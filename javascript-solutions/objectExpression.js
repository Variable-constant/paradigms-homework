"use strict"

const VARIABLES = new Map([
    ["x", 0],
    ["y", 1],
    ["z", 2]
])

const OPERATIONS = new Map()
const ARGUMENTS_NUMBER = new Map()

const makeExpression = function(expression, evaluate, diff, toString, prefix, postfix) {
    expression.prototype = {
        evaluate: evaluate,
        diff: diff,
        toString: toString,
        prefix: prefix,
        postfix: postfix
    }
}

const Const = function(value) {
    this.value = value;
}

makeExpression(
    Const,
    function() {return this.value},
    () => ZERO,
    function() {return this.value.toString()},
    function() {return this.value.toString()},
    function() {return this.value.toString()}
);

const ZERO = new Const(0);
const ONE = new Const(1);
const TWO = new Const(2);

const Variable = function(variable) {
    this.variable = variable;
    this.index = VARIABLES.get(variable);
}

makeExpression(
    Variable,
    function(...args) {return args[this.index]},
    function(d) {return d === this.variable ? ONE : ZERO},
    function() {return this.variable},
    function() {return this.variable},
    function() {return this.variable}
)

const makeOperation = function(evaluate, diffChild, character) {
    let operation = function(...operands) {
        this.operands = operands;
    }
    makeExpression(
        operation,
        function(...args) {
            return evaluate(...this.operands.map(elem => elem.evaluate(...args)));
        },
        function(d) {return diffChild(d, ...this.operands)},
        function () {
            return this.operands.concat(character).join(' ');
        },
        function() {
            return '(' + [character].concat(this.operands.map(op => op.prefix())).join(' ') + ')';
        },
        function() {
            return '(' + this.operands.map(op => op.postfix()).concat(character).join(' ') + ')';
        }
    )
    OPERATIONS.set(character, (...args) => new operation(...args));
    ARGUMENTS_NUMBER.set(character, evaluate.length);
    return operation;
}

const Negate = makeOperation(
    a => -a,
    (d, exp) => new Negate(exp.diff(d)),
    'negate'
);

const Add = makeOperation(
    (a, b) => a + b,
    (d, a, b) => new Add(a.diff(d), b.diff(d)),
    '+'
);

const Subtract = makeOperation(
    (a, b) => a - b,
    (d, left, right) => new Subtract(left.diff(d), right.diff(d)),
    '-'
)

const Multiply = makeOperation(
    (a, b) => a * b,
    (d, left, right) => new Add(
        new Multiply(
            left.diff(d),
            right
        ),
        new Multiply(
            left,
            right.diff(d)
        )
    ),
    '*'
)

const Divide = makeOperation(
    (a, b) => a / b,
    (d, left, right) => new Divide(
        new Subtract(
            new Multiply(
                left.diff(d),
                right
            ),
            new Multiply(
                left,
                right.diff(d)
            )
        ),
        new Multiply(
            right,
            right
        )
    ),
    '/'
)

const Hypot = makeOperation(
    (a, b) => a * a + b * b,
    (d, left, right) => new Add(
        new Multiply(left, left),
        new Multiply(right, right),
    ).diff(d),
    'hypot'
)

const HMean = makeOperation(
    (a, b) => 2 / (1 / a + 1 / b),
    (d, left, right) => new Divide(
        TWO,
        new Add(
            new Divide(ONE, left),
            new Divide(ONE, right)
        ),
    ).diff(d),
    'hmean'
)

const ArithMean = makeOperation(
    (...args) => args.reduce((a, b) => a + b, 0) / args.length,
    (d, ...args) => new ArithMean(...args.map(arg => arg.diff(d))),
    'arith-mean'
)

const GeomMean = makeOperation(
    (...args) => Math.pow(Math.abs(args.reduce((a, b) => a * b, 1)), 1.0 / args.length),
    (d, ...args) => {
        let mul = args.reduce((a, b) => new Multiply(a, b), ONE);
        return new Divide(
            new Multiply(
                mul.diff(d),
                new GeomMean(...args)
                ),
            new Multiply(
                new Const(args.length),
                mul
                )
            )
    },
    'geom-mean',
)

const HarmMean = makeOperation(
    (...args) => args.length / args.reduce((a, b) => a + 1 / b, 0),
    (d, ...args) => new Divide(
        new Const(args.length),
        args.reduce((a, b) => new Add(a, new Divide(ONE, b)), ZERO)).diff(d),
    'harm-mean'
)

function parse(str) {
    let stack = [];
    for (let elem of str.split(' ').filter((x) => x !== '')) {
        if (OPERATIONS.has(elem)) {
            stack.push(OPERATIONS.get(elem)(...stack.splice(-ARGUMENTS_NUMBER.get(elem))));
        } else if (VARIABLES.has(elem)) {
            stack.push(new Variable(elem));
        } else {
            stack.push(new Const(+elem));
        }
    }
    return stack.pop();
}

function makeParsingError(msg) {
    function ParsingError(message) {
        this.message = message + ' ' + msg;
    }
    ParsingError.prototype = Object.create(Error.prototype);
    ParsingError.prototype.name = ParsingError;
    ParsingError.prototype.constructor = ParsingError;
    return ParsingError;
}

// :NOTE: const
const ExpectedClosingBracketError = makeParsingError('Expected closing bracket');
const ExpectedOperationError = makeParsingError('Expected operation');
const ExtraClosingBracketError = makeParsingError('Extra closing bracket');
const UnexpectedExpressionError = makeParsingError('Unexpected expression');
const ExtraCharactersError = makeParsingError('Extra characters at the end');
const EmptyInputError = makeParsingError('Empty input');
const ArityError = makeParsingError('Operation has different arity');

// :NOTE: Missing (                : org.graalvm.polyglot.PolyglotException: ParsingError: <--- Expected closing bracket
// :NOTE: сколько получили аргументов
function Parser(mode) {
    let balance = 0;
    let source;
    let position;
    let current;
    let copy;
    let openingBracket = mode === 'prefix' ? '(' : ')';
    let closingBracket = mode === 'prefix' ? ')' : '(';
    let next = mode === 'prefix' ?
        () => {let tmp = source.shift(); position++; current = source[0]; return tmp}
        : () => {let tmp = source.pop(); position--; current = source[source.length - 1]; return tmp};
    function parse() {
        if (current === openingBracket) {
            balance++;
            next();
            let op = current;
            let opPos = position;
            if (OPERATIONS.has(op)) {
                let buf = [];
                next();
                while (source.length !== 0 && current !== closingBracket) {
                    buf.push(parse());
                }
                if (source.length === 0) {
                    throw new ExpectedClosingBracketError(mode === 'prefix' ?
                        copy.slice(Math.max(0, position - 10), position).join(' ') + '<---' :
                        '---> ' + copy.slice(position + 1, position + 10).join(' '));
                }
                if (ARGUMENTS_NUMBER.get(op) !== 0 && buf.length !== ARGUMENTS_NUMBER.get(op)) {
                    position = opPos;
                    throw new ArityError(showPosition() + ' ' + 'expected ' + ARGUMENTS_NUMBER.get(op) + ' arguments, found ' + buf.length + '.');
                }
                parse();
                return OPERATIONS.get(op)(...(mode === 'prefix' ? buf : buf.reverse()));
            } else {
                throw new ExpectedOperationError(showPosition());
            }
        } else if (VARIABLES.has(current)) {
            let res = current;
            next();
            return new Variable(res);
        } else if (!isNaN(current)) {
            let res = current;
            next();
            return new Const(+res);
        } else if (current === closingBracket) {
            balance--;
            next();
            if (balance < 0) {
                throw new ExtraClosingBracketError(showPosition());
            }
        } else {
            throw new UnexpectedExpressionError(showPosition());
        }
    }

    function res(str) {
        source = str.match(/[^\s()]+|[()]/g);
        if (source === null) {
            throw new EmptyInputError('Nothing found');
        }
        copy = [...source];
        current = mode === 'prefix' ? source[0] : source[source.length - 1];
        position = mode === 'prefix' ? 0 : source.length - 1;
        let result = parse();
        if (source.length !== 0) {
            throw new ExtraCharactersError(showPosition());
        }
        return result;
    }

    function showPosition() {
        return copy.slice(
                    Math.max(0, position - 10), position)
                    .concat('---> ' + copy[position] + ' <---')
                    .concat(copy.slice(position + 1, position + 10)).join(' ');
    }

    return res;
}
const parsePrefix = Parser('prefix');
const parsePostfix = Parser('postfix');
// let expr = parsePrefix('(+ x)');
