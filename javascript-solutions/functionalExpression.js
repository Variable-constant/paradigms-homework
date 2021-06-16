"use strict";
const VARIABLES = {"x": 0, "y": 1, "z": 2}

const cnst = value => () => value;
const variable = v => {
    let index = VARIABLES[v];
    return (...args) => args[index];
};

const operator = f => (...operations) => (...varibles) => f(...operations.map(op => op(...varibles)));

const add = operator((a, b) => a + b);
const subtract = operator((a, b) => a - b);
const multiply = operator((a, b) => a * b);
const divide = operator((a, b) => a / b);
const negate = operator((a) => -a);
const madd = operator((a, b, c) => a * b + c);
const ceil = operator(Math.ceil);
const floor = operator(Math.floor);

const one = cnst(1)
const two = cnst(2)

const OPERATIONS = {
    "+": add,
    "-": subtract,
    "*": multiply,
    "/": divide,
    "negate": negate,
    "madd": madd,
    "*+": madd,
    "floor": floor,
    "_": floor,
    "ceil": ceil,
    "^": ceil
};

const ARGUMENTS_NUMBER = {
    "+": 2,
    "-": 2,
    "*": 2,
    "/": 2,
    "negate": 1,
    "madd": 3,
    "*+": 3,
    "floor": 1,
    "_": 1,
    "ceil": 1,
    "^": 1
};

const CONSTANTS = {
    "one": one,
    "two": two
}
function parse(str) {
    let stack = [];
    for (let elem of str.split(' ').filter(x => x !== '')) {
        if (elem in OPERATIONS) {
            stack.push(OPERATIONS[elem](...stack.splice(-ARGUMENTS_NUMBER[elem])));
        } else if (elem in VARIABLES) {
            stack.push(variable(elem));
        } else if (elem in CONSTANTS) {
            stack.push(CONSTANTS[elem]);
        } else {
            stack.push(cnst(+elem));
        }
    }
    return stack.pop();
}
