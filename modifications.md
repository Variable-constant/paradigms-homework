## Домашнее задание 12. Простые числа на Prolog

Модификации
 * *Базовая*
    * Код должен находиться в файле `prolog-solutions/primes.pl`.
    * [Исходный код тестов](tests/prolog/prtest/primes/PrologPrimesTest.java)
        * Запускать c аргументом `easy`, `hard` или `bonus`
 * *Square* (32-33)
    * Добавьте правило `square_divisors(N, D)`, возвращающее делители N²:
      `square_divisors(6, [2, 2, 3, 3])`.
    * [Исходный код тестов](tests/prolog/prtest/primes/PrologSquareTest.java)
 * *Power* (34-35)
    * Добавьте правило `power_divisors(N, I, D)`, возвращающее делители Nⁱ:
      `power_divisors(6, 2, [2, 2, 3, 3])`.
    * [Исходный код тестов](tests/prolog/prtest/primes/PrologPowerTest.java)
 * *Gcd* (36-37)
    * Добавьте правило `gcd(A, B, GCD)`,
      подсчитывающее НОД(`A`, `B`) через разложение на простые множители:
      `gcd(4, 6, 2)`.
    * [Исходный код тестов](tests/prolog/prtest/primes/PrologGcdTest.java)
 * *Index* (38-39)
    * Добавьте правило `prime_index(P, N)`, подсчитывающее номер простого числа:
        `prime_index(2, 1)`, `prime_index(101, 26)`
    * [Исходный код тестов](tests/prolog/prtest/primes/PrologIndexTest.java)

Для запуска тестов можно использовать скрипты
[TestProlog.cmd](tests/prolog/TestProlog.cmd) и [TestProlog.sh](tests/prolog/TestProlog.sh)
 * Репозиторий должен быть скачан целиком.
 * Скрипты должны находиться в каталоге `prolog`
    (их нельзя перемещать, но можно вызывать из других каталогов).
 * Полное имя класса теста указывается в качестве первого аргумента командной строки,
    например, `prtest.primes.PrologPrimesTest`.
 * Тестируемое решение должно находиться в текущем каталоге.


## Исходный код к лекциям по Prolog

Запуск Prolog
 * [Windows](tests/prolog/RunProlog.cmd)
 * [*nix](tests/prolog/RunProlog.sh)

Лекция 1. Факты, правила и вычисления
 * [Учебный план](tests/prolog/examples/1_1_plan.pl)
 * [Вычисления](tests/prolog/examples/1_2_calc.pl)
 * [Списки](tests/prolog/examples/1_3_lists.pl)
 * [Правила высшего порядка](tests/prolog/examples/1_4_high-order.pl)

Лекция 2. Задачи, унификация и объекты
 * [Задача о расстановке ферзей](tests/prolog/examples/2_1_queens.pl)
 * [Задача Эйнштейна](tests/prolog/examples/2_2_einstein.pl)
 * [Арифметические выражения](tests/prolog/examples/2_3_expressions.pl)


## Домашнее задание 11. Комбинаторные парсеры

Модификации
 * *Базовая*
    * Код должен находиться в файле `clojure-solutions/expression.clj`.
    * [Исходный код тестов](tests/clojure/cljtest/parsing/ParserTest.java)
        * Запускать c указанием модификации и сложности (`easy` или `hard`).
 * *Variables* (32-33). Дополнительно реализовать поддержку:
    * Переменных, состоящих из произвольного количества букв `XYZ` в любом регистре
        * Настоящее имя переменной определяется первой буквой ее имени
 * *Boolean* (34-35). Сделать модификацию *Variables* и дополнительно реализовать поддержку:
    * Булевских операций
        * Аргументы: число больше 0 → `true`, иначе → `false`
        * Результат: `true` → 1, `false` → 0
        * `And` (`&&`) – и: `5 & -6` равно 0
        * `Or`  (`||`) - или: `5 & -6` равно 1
        * `Xor` (`^^`) - исключающее или: `5 ^ -6` равно 1
        * операции по увеличению приоритета: `^^`, `||`, `&&`, операции базовой модификации
 * *PowLog* (36-37). Сделать модификацию *Variables* и дополнительно реализовать поддержку:
    * Бинарных правоассоциативных операций максимального приоритета:
        * `IPow` (`**`) – возведения в степень:
            `4 ** 3 ** 2` равно `4 ** (3 ** 2)` равно 262144
        * `ILog` (`//`) – взятия логарифма:
            `8 // 9 // 3` равно `8 // (9 // 3)` равно 3
 * *ImplIff* (38-39). Сделать модификацию *Boolean* и дополнительно реализовать поддержку:
    * Булевских операций
        * `Impl` (`->`) – импликация (правоассоциативна): `-4 -> 1` равно 1
        * `Iff`  (`<->`) - тогда и только тогда: `2 <-> 6` равно 1
        * операции по увеличению приоритета: `<->`, `->`, операции модификации *Boolean*


## Домашнее задание 10. Объектные выражения на Clojure

Модификации
 * *Base*
    * Код должен находиться в файле `clojure-solutions/expression.clj`.
    * [Исходный код тестов](tests/clojure/cljtest/object/ObjectTest.java)
        * Запускать c указанием модификации и сложности (`easy` или `hard`).
 * *SinCos* (32-33). Дополнительно реализовать поддержку:
    * унарных операций:
        * `Sin` (`sin`) – синус, `(sin 4846147)` примерно равно 1;
        * `Cos` (`cos`) – косинус, `(cos 5419351)` примерно равно 1.
 * *SinhCosh* (34-35). Дополнительно реализовать поддержку:
    * унарных операций:
        * `Sinh` (`sinh`) – гиперболический синус, `(sinh 3)` немного больше 10;
        * `Cosh` (`cosh`) – гиперболический косинус, `(cosh 3)` немного меньше 10.
 * *SumAvg* (36-37). Дополнительно реализовать поддержку:
    * операций произвольного числа аргументов:
        * `Sum` (`sum`) – сумма, `(sum 1 2 3)` равно 6;
        * `Avg` (`avg`) – арифметическое среднее, `(avg 1 2 3)` равно 2;
 * *Means* (38-39). Дополнительно реализовать поддержку:
    * операций произвольного числа аргументов:
        * `ArithMean` (`arith-mean`) – арифметическое среднее `(arith-mean 1 2 6)` равно 3;
        * `GeomMean` (`geom-mean`) – геометрическое среднее `(geom-mean 1 2 4)` равно 2;
        * `HarmMean` (`harm-mean`) – гармоническое среднее, `(harm-mean 2 3 6)` равно 3;


## Домашнее задание 9. Функциональные выражения на Clojure

Модификации
 * *Base*
    * Код должен находиться в файле `clojure-solutions/expression.clj`.
    * [Исходный код тестов](tests/clojure/cljtest/functional/FunctionalTest.java)
        * Запускать c указанием модификации и сложности (`easy` или `hard`).
 * *SinCos* (32-33). Дополнительно реализовать поддержку:
    * унарных операций:
        * `sin` – синус, `(sin 4846147)` примерно равно 1;
        * `cos` – косинус, `(cos 5419351)` примерно равно 1.
 * *SinhCosh* (34-35). Дополнительно реализовать поддержку:
    * унарных операций:
        * `sinh` – гиперболический синус, `(sinh 3)` немного больше 10;
        * `cosh` – гиперболический косинус, `(cosh 3)` немного меньше 10.
 * *SumAvg* (36-37). Дополнительно реализовать поддержку:
    * операций произвольного числа аргументов:
        * `sum` – сумма, `(sum 1 2 3)` равно 6;
        * `avg` – среднее, `(avg 1 2 3)` равно 2;
 * *MeanVarn* (38-39). Дополнительно реализовать поддержку:
    * операций произвольного числа аргументов:
        * `mean` – математическое ожидание аргументов, `(mean 1 2 6)` равно 3;
        * `varn` – дисперсия аргументов, `(varn 2 5 11)` равно 14;


## Домашнее задание 8. Линейная алгебра на Clojure

Модификации
 * *Базовая*
    * Код должен находиться в файле `clojure-solutions/linear.clj`.
    * [Исходный код тестов](tests/clojure/cljtest/linear/LinearTest.java)
        * Запускать c аргументом `easy` или `hard`
 * *Cuboid* (32-33)
    * Назовем _кубоидом_ трехмерную прямоугольную таблицу чисел.
    * Добавьте операции поэлементного сложения (`c+`),
        вычитания (`c-`), умножения (`c*`) и деления (`cd`) кубоидов.
        Например, `(с+ [[[1] [2]] [[3] [4]]] [[[5] [6]] [[7] [8]]])` должно быть равно `[[[6] [8]] [[10] [12]]]`.
    * [Исходный код тестов](tests/clojure/cljtest/linear/CuboidTest.java)
 * *Tensor* (34-35)
    * Назовем _тензором_ многомерную прямоугольную таблицу чисел.
    * Добавьте операции поэлементного сложения (`t+`),
        вычитания (`t-`) и умножения (`t*`) тензоров.
        Например, `(t+ [[1 2] [3 4]] [[5 6] [7 8]])` должно быть равно `[[6 8] [10 12]]`.
    * [Исходный код тестов](tests/clojure/cljtest/linear/TensorTest.java)
 * *Simplex* (36-37)
    * Назовем _симплексом_ многомерную таблицу чисел, 
      такую что для некоторого `n` в ней существуют все значения
      с суммой индексов не превышающей `n` и только эти значения.
    * Добавьте операции поэлементного сложения (`x+`),
        вычитания (`x-`) и умножения (`x*`) симплексов.
        Например, `(x+ [[1 2] [3]] [[5 6] [7]])` должно быть равно `[[6 8] [10]]`.
    * [Исходный код тестов](tests/clojure/cljtest/linear/SimplexTest.java)
 * *Broadcast* (38-39)
    * Назовем _тензором_ многомерную прямоугольную таблицу чисел.
    * _Форма_ тензора – последовательность чисел
        (_s_<sub>1..n</sub>)=(_s_<sub>1</sub>, _s_<sub>2</sub>, …, _s<sub>n</sub>_), где
        _n_ – размерность тензора, а _s<sub>i</sub>_ – число элементов
        по _i_-ой оси.
      Например, форма тензора `[[[2 3 4] [5 6 7]]]`  равна (1, 2, 3),
      а форма `1` равна ().
    * Тензор формы (_s_<sub>1.._n_</sub>) может быть _распространен_ (broadcast)
      до тензора формы (_u_<sub>1.._m_</sub>), если (_s_<sub>i.._n_</sub>) является
      префиксом (_u<sub>1..m</sub>_). 
      Для этого, элементы тензора копируются по недостающим осям.
      Например, распространив тензор `[[1 2]]` формы (1, 2) до
      формы (1, 2, 3) получим `[[[1 1 1] [2 2 2]]]`,
      а распространив `1` до формы (2, 3) получим `[[1 1 1] [1 1 1]]`.
    * Тензоры называются совместимыми, если один из них может быть распространен
      до формы другого.
      Например, тензоры формы (1, 2, 3) и (1, 2) совместимы, а
      (1, 2, 3) и (2, 1) – нет. Числа совместимы с тензорами любой формы.
    * Добавьте операции поэлементного 
      сложения (`tb+`), вычитания (`tb-`), умножения (`tb*`) и деления (`tbd`)
      совместимых тензоров.
      Если формы тензоров не совпадают, то тензоры меньшей размерности
      должны быть предварительно распространены до тензоров большей размерности.
      Например, `(tb+ 1 [[10 20 30] [40 50 60]] [100 200])` 
      должно быть равно `[[111 121 131] [241 251 261]]`.
    * [Исходный код тестов](tests/clojure/cljtest/linear/BroadcastTest.java)


Для запуска тестов можно использовать скрипты
[TestClojure.cmd](tests/clojure/TestClojure.cmd) и [TestClojure.sh](tests/clojure/TestClojure.sh)
 * Репозиторий должен быть скачан целиком.
 * Скрипты должны находиться в каталоге `clojure`
    (их нельзя перемещать, но можно вызывать из других каталогов).
 * Полное имя класса теста указывается в качестве аргумента командной строки,
    например, `cljtest.linear.LinearTest`.
 * Тестируемое решение должно находиться в текущем каталоге.


## Исходный код к лекциям по Clojure

Запуск Clojure
 * Консоль: [Windows](tests/clojure/RunClojure.cmd), [*nix](tests/clojure/RunClojure.sh)
    * Интерактивный: `RunClojure`
    * С выражением: `RunClojure --eval "<выражение>"`
    * Скрипт: `RunClojure <файл скрипта>`
    * Справка: `RunClojure --help`
 * IDE
    * IntelliJ Idea: [плагин Cursive](https://cursive-ide.com/userguide/)
    * Eclipse: [плагин Counterclockwise](https://doc.ccw-ide.org/documentation.html)

[Скрипт со всеми примерами](tests/clojure/examples.clj)

Лекция 1. Функции
 * [Введение](tests/clojure/examples/1_1_intro.clj)
 * [Функции](tests/clojure/examples/1_2_functions.clj)
 * [Списки](tests/clojure/examples/1_3_lists.clj)
 * [Вектора](tests/clojure/examples/1_4_vectors.clj)
 * [Функции высшего порядка](tests/clojure/examples/1_5_functions-2.clj)

Лекция 2. Внешний мир
 * [Ввод-вывод](tests/clojure/examples/2_1_io.clj)
 * [Разбор и гомоиконность](tests/clojure/examples/2_2_read.clj)
 * [Порядки вычислений](tests/clojure/examples/2_3_evaluation-orders.clj)
 * [Потоки](tests/clojure/examples/2_4_streams.clj)
 * [Отображения и множества](tests/clojure/examples/2_5_maps.clj)

Лекция 3. Объекты
 * [Прототипное наследование](tests/clojure/examples/3_1_js-objects.clj)
 * [Java-классы](tests/clojure/examples/3_2_java-objects.clj)

Лекция 4. Разное
 * [Макросы](tests/clojure/examples/4_1_macro.clj)
 * [Макросы для парсеров](tests/clojure/examples/5_4_macro.clj)
 * [Изменяемое состояние](tests/clojure/examples/4_2_mutable-state.clj)
 * [Числа Чёрча](tests/clojure/examples/4_3_church.clj)

Лекция 5. Комбинаторные парсеры
 * [Базовые функции](tests/clojure/examples/5_1_base.clj)
 * [Комбинаторы](tests/clojure/examples/5_2_combinators.clj)
 * [JSON](tests/clojure/examples/5_3_json.clj)


## Домашнее задание 7. Обработка ошибок на JavaScript

Модификации
 * *Base*
    * Код должен находиться в файле `javascript-solutions/objectExpression.js`.
    * [Исходный код тестов](tests/javascript/jstest/prefix/ParserTest.java)
        * Запускать c указанием модификации и сложности (`easy` или `hard`).
 * *Prefix*: *SinhCosh* (32-33). Дополнительно реализовать поддержку:
    * унарных операций:
        * `Sinh` (`sinh`) – гиперболический синус, `(sinh 3)` немного больше 10;
        * `Cosh` (`cosh`) – гиперболический косинус, `(cosh 3)` немного меньше 10;
    * [Исходный код тестов](tests/javascript/jstest/prefix/PrefixTest.java)
 * *Prefix*: *Means* (34-35). Дополнительно реализовать поддержку:
    * операций произвольного числа аргументов:
        * `ArithMean` (`arith-mean`) – арифметическое среднее `(arith-mean 1 2 6)` равно 3;
        * `GeomMean` (`geom-mean`) – геометрическое среднее `(geom-mean 1 2 4)` равно 2;
        * `HarmMean` (`harm-mean`) – гармоническое среднее, `(harm-mean 2 3 6)` равно 3;
    * [Исходный код тестов](tests/javascript/jstest/prefix/PrefixTest.java)
 * *Postfix*: *SumsqLength* (36-37). Дополнительно реализовать поддержку:
    * выражений в постфиксной записи: `(2 3 +)` равно 5
    * операций произвольного числа аргументов:
        * `Sumsq` (`sumsq`) – сумма квадратов, `(1 2 3 sumsq)` равно 14;
        * `Length` (`length`у) – длина вектора, `(3 4 length)` равно 5;
    * [Исходный код тестов](tests/javascript/jstest/prefix/PostfixTest.java)
 * *Postfix*: *Means* (38-39). Дополнительно реализовать поддержку:
    * операций произвольного числа аргументов:
        * `ArithMean` (`arith-mean`) – арифметическое среднее `(arith-mean 1 2 6)` равно 3;
        * `GeomMean` (`geom-mean`) – геометрическое среднее `(geom-mean 1 2 4)` равно 2;
        * `HarmMean` (`harm-mean`) – гармоническое среднее, `(harm-mean 2 3 6)` равно 3;
    * [Исходный код тестов](tests/javascript/jstest/prefix/PostfixTest.java)


## Домашнее задание 6. Объектные выражения на JavaScript

Модификации
 * *Base*
    * Код должен находиться в файле `javascript-solutions/objectExpression.js`.
    * [Исходный код тестов](tests/javascript/jstest/object/ObjectTest.java)
        * Запускать c указанием модификации и сложности (`easy`, `hard` или `bonus`).
 * *ArcTan* (32, 33). Дополнительно реализовать поддержку:
    * функций:
        * `ArcTan` (`atan`) – арктангенс, `1256 atan` примерно равно 1.57;
        * `ArcTan2` (`atan2`) – арктангенс, `841 540 atan2` примерно равно 1;
 * *AvgMed* (34, 35). Дополнительно реализовать поддержку:
    * функций:
        * `Avg5` (`avg5`) – арифметическое среднее пяти аргументов, `1 2 3 4 5 avg5` равно 3;
        * `Med3` (`med3`) – медиана трех аргументов, `1 2 -10 med3` равно 1.
 * *Cube* (36, 37). Дополнительно реализовать поддержку:
    * унарных функций:
        * `Cube` (`cube`) – возведение в куб, `3 cube` равно 27;
        * `Cbrt` (`cbrt`) – извлечение кубического корня, `-27 cbrt` равно −3;
 * *Harmonic* (38, 39). Дополнительно реализовать поддержку:
    * функций от двух аргументов:
        * `Hypot` (`hypot`) – квадрат гипотенузы, `3 4 hypot` равно 25;
        * `HMean` (`hmean`) – гармоническое среднее, `5 20 hmean` равно 8;


## Домашнее задание 5. Функциональные выражения на JavaScript

Модификации
 * *Базовая*
    * Код должен находиться в файле `javascript-solutions/functionalExpression.js`.
    * [Исходный код тестов](tests/javascript/jstest/functional/ExpressionTest.java)
        * Запускать c аргументом `hard` или `easy`;
 * *Mini* (для тестирования)
    * Не поддерживаются бинарные операции
    * Код находится в файле [functionalMiniExpression.js](tests/javascript/MiniExpression.js).
    * [Исходный код тестов](tests/javascript/jstest/functional/MiniTest.java)
        * Запускать c аргументом `hard` или `easy`;
 * *Variables* (32, 33). Дополнительно реализовать поддержку:
    * переменных: `y`, `z`;
    * [Исходный код тестов](tests/javascript/jstest/functional/FunctionalTest.java)
 * *OneTwo* (34, 35). Дополнительно реализовать поддержку:
    * переменных: `y`, `z`;
    * констант:
        * `one` – 1;
        * `two` – 2;
    * [Исходный код тестов](tests/javascript/jstest/functional/FunctionalTest.java)
 * *OneMinMax* (36, 37). Дополнительно реализовать поддержку:
    * переменных: `y`, `z`;
    * констант:
        * `one` – 1;
        * `two` – 2;
    * операций:
        * `min5` – минимальный из пяти аргументов, `3 1 4 0 2 min5` равно 0;
        * `max3` – максимальный из трех аргументов, `3 1 4 max3` равно 4.
    * [Исходный код тестов](tests/javascript/jstest/functional/FunctionalTest.java)
 * *OneFP* (38, 39). Дополнительно реализовать поддержку:
    * переменных: `y`, `z`;
    * констант:
        * `one` – 1;
        * `two` – 2;
    * операций:
        * `*+` (`madd`) – тернарный оператор произведение-сумма, `2 3 4 *+` равно 10;
        * `_` (`floor`) – округление вниз `2.7 _` равно 2;
        * `^` (`ceil`) – округление вверх `2.7 ^` равно 3.
    * [Исходный код тестов](tests/javascript/jstest/functional/FunctionalTest.java)

Запуск тестов
 * Для запуска тестов используется [GraalJS](https://github.com/graalvm/graaljs)
   (часть проекта [GraalVM](https://www.graalvm.org/), вам не требуется их скачивать отдельно)
 * Для запуска тестов можно использовать скрипты [TestJS.cmd](tests/javascript/TestJS.cmd) и [TestJS.sh](tests/javascript/TestJS.sh)
    * Репозиторий должен быть скачан целиком.
    * Скрипты должны находиться в каталоге `javascript` (их нельзя перемещать, но можно вызывать из других каталогов).
    * В качестве аргументов командной строки указывается полное имя класса теста и модификация, 
      например `jstest.functional.FunctionalExpressionTest hard`.
 * Для самостоятельно запуска из консоли необходимо использовать командную строку вида:
    `java -ea --module-path=<js>/graal --class-path <js> jstest.functional.FunctionalExpressionTest {hard|easy}`, где
    * `-ea` – включение проверок времени исполнения;
    * `--module-path=<js>/graal` путь к модулям Graal (здесь и далее `<js>` путь к каталогу `javascript` этого репозитория);
    * `--class-path <js>` путь к откомпилированным тестам;
    * {`hard`|`easy`} указание тестируемой модификации.
 * При запуске из IDE, обычно не требуется указывать `--class-path`, так как он формируется автоматически.
   Остальные опции все равно необходимо указать.
 * Troubleshooting
    * `Error occurred during initialization of boot layer java.lang.module.FindException: Module org.graalvm.truffle not found, required by jdk.internal.vm.compiler` – неверно указан `--module-path`;
    * `Graal.js not found` – неверно указаны `--module-path`
    * `Error: Could not find or load main class jstest.functional.FunctionalExpressionTest` – неверно указан `--class-path`;
    * `Error: Could not find or load main class <other class>` – неверно указано полное имя класса теста;
    * `Exception in thread "main" java.lang.AssertionError: You should enable assertions by running 'java -ea jstest.functional.FunctionalExpressionTest'` – не указана опция `-ea`;
    * `First argument should be one of: "easy", "hard", found: XXX` – неверно указана сложность;
    * `Exception in thread "main" jstest.EngineException: Script 'functionalExpression.js' not found` – в текущем каталоге отсутствует решение (`functionalExpression.js`)


## Исходный код к лекциям по JavaScript

[Скрипт с примерами](tests/javascript/examples.js)

Запуск примеров
 * [В браузере](tests/javascript/RunJS.html)
 * Из консоли
    * [на Java](tests/javascript/RunJS.java): [RunJS.cmd](tests/javascript/RunJS.cmd), [RunJS.sh](tests/javascript/RunJS.sh)
    * [на node.js](tests/javascript/RunJS.node.js): `node RunJS.node.js`

Лекция 1. Типы и функции
 * [Типы](tests/javascript/examples/1_1_types.js)
 * [Функции](tests/javascript/examples/1_2_functions.js)
 * [Функции высшего порядка](tests/javascript/examples/1_3_functions-hi.js).
   Обратите внимание на реализацию функции `mCurry`.

Лекция 2. Объекты и методы
 * [Объекты](tests/javascript/examples/2_1_objects.js)
 * [Замыкания](tests/javascript/examples/2_2_closures.js)
 * [Модули](tests/javascript/examples/2_3_modules.js)
 * [Пример: стеки](tests/javascript/examples/2_4_stacks.js)

Лекция 3. Другие возможности
 * [Обработка ошибок](tests/javascript/examples/3_1_errors.js)
 * [Чего нет в JS](tests/javascript/examples/3_2_no.js)
 * [Стандартная библиотека](tests/javascript/examples/3_3_builtins.js)
 * [Работа со свойствами](tests/javascript/examples/3_4_properties.js)
 * [Методы и классы](tests/javascript/examples/3_5_classes.js)
 * [JS 6+](tests/javascript/examples/3_6_js6.js)
 * Модули: 
   [объявление](tests/javascript/examples/3_7_js6_module.mjs)
   [использование](tests/javascript/examples/3_7_js6_module_usage.mjs)


## Домашнее задание 4. Вычисление в различных типах

Модификации
 * *Базовая*
    * Класс `GenericTabulator` должен реализовывать интерфейс
      [Tabulator](tests/java/expression/generic/Tabulator.java) и
      сроить трехмерную таблицу значений заданного выражения.
        * `mode` – режим вычислений:
           * `i` – вычисления в `int` с проверкой на переполнение;
           * `d` – вычисления в `double` без проверки на переполнение;
           * `bi` – вычисления в `BigInteger`.
        * `expression` – выражение, для которого надо построить таблицу;
        * `x1`, `x2` – минимальное и максимальное значения переменной `x` (включительно)
        * `y1`, `y2`, `z1`, `z2` – аналогично для `y` и `z`.
        * Результат: элемент `result[i][j][k]` должен содержать
          значение выражения для `x = x1 + i`, `y = y1 + j`, `z = z1 + k`.
          Если значение не определено (например, по причине переполнения),
          то соответствующий элемент должен быть равен `null`.
    * [Исходный код тестов](tests/java/expression/generic/GenericTest.java)
 * *Ufb* (32-33)
    * Дополнительно реализовать поддержку режимов:
        * `u` – вычисления в `int` без проверки на переполнение;
        * `f` – вычисления в `float` без проверки на переполнение;
        * `b` – вычисления в `byte` без проверки на переполнение.
    * [Исходный код тестов](tests/java/expression/generic/GenericUfbTest.java)
 * *Uls* (34-35)
    * Дополнительно реализовать поддержку режимов:
        * `u` – вычисления в `int` без проверки на переполнение;
        * `l` – вычисления в `long` без проверки на переполнение;
        * `s` – вычисления в `short` без проверки на переполнение.
    * [Исходный код тестов](tests/java/expression/generic/GenericUlsTest.java)
 * *AsmUls* (36-7)
    * Реализовать режимы из модификации *Uls*.
    * Дополнительно реализовать унарные операции:
        * `abs` – модуль числа, `abs -5` равно 5;
        * `square` – возведение в квадрат, `square 5` равно 25.
    * Дополнительно реализовать бинарную операцию (максимальный приоритет):
        * `mod` – взятие по модулю, приоритет как у умножения (`1 + 5 mod 3` равно `1 + (5 mod 3)` равно `3`).
    * [Исходный код тестов](tests/java/expression/generic/GenericAsmUlsTest.java)
 * *AsmUpb* (38-39)
    * Дополнительно реализовать унарные операции:
        * `abs` – модуль числа, `abs -5` равно 5;
        * `square` – возведение в квадрат, `square 5` равно 25.
    * Дополнительно реализовать бинарную операцию (максимальный приоритет):
        * `mod` – взятие по модулю, приоритет как у умножения (`1 + 5 mod 3` равно `1 + (5 mod 3)` равно `3`).
    * Дополнительно реализовать поддержку режимов:
        * `u` – вычисления в `int` без проверки на переполнение;
        * `p` – вычисления в целых числах по модулю 1009;
        * `b` – вычисления в `byte` без проверки на переполнение.
    * [Исходный код тестов](tests/java/expression/generic/GenericAsmUpbTest.java)


## Домашнее задание 3. Очередь на связном списке

Модификации
 * *Базовая*
    * [Исходный код тестов](tests/java/queue/QueueTest.java)
    * [Откомпилированные тесты](tests/artifacts/queue/QueueTest.jar)
 * *ToArray* (32-33)
    * Добавить в интерфейс очереди и реализовать метод `toArray`, 
      возвращающий массив, содержащий элементы, лежащие в очереди 
      в порядке от головы к хвосту.
    * [Исходный код тестов](tests/java/queue/QueueToArrayTest.java)
    * [Откомпилированные тесты](tests/artifacts/queue/QueueToArrayTest.jar)
 * *IndexedToArray* (34-35)
    * Реализовать методы
        * `get` – получить элемент по индексу, отсчитываемому с головы;
        * `set` – заменить элемент по индексу, отсчитываемому с головы;
        * `toArray`, возвращающий массив, содержащий элементы, 
          лежащие в очереди в порядке от головы к хвосту.
    * [Исходный код тестов](tests/java/queue/QueueIndexedToArrayTest.java)
    * [Откомпилированные тесты](tests/artifacts/queue/QueueIndexedToArrayTest.jar)
 * *Contains* (36-37)
    * Добавить в интерфейс очереди и реализовать методы
        * `contains(element)` – проверяет, содержится ли элемент в очереди
        * `removeFirstOccurrence(element)` – удаляет первое вхождение элемента в очередь 
            и возвращает было ли такое
    * Дублирования кода быть не должно
    * [Исходный код тестов](tests/java/queue/QueueContainsTest.java)
    * [Откомпилированные тесты](tests/artifacts/queue/QueueContainsTest.jar)
 * *Nth* (38-39)
    * Добавить в интерфейс очереди и реализовать методы
        * `getNth(n)` – создать очередь, содержащую каждый n-й элемент, считая с 1
        * `removeNth(n)` – создать очередь, содержащую каждый n-й элемент, и удалить их из исходной очереди
        * `dropNth(n)` – удалить каждый n-й элемент из исходной очереди
    * Тип возвращаемой очереди должен соответствовать типу исходной очереди
    * Дублирования кода быть не должно
    * [Исходный код тестов](tests/java/queue/QueueNthTest.java)
    * [Откомпилированные тесты](tests/artifacts/queue/QueueNthTest.jar)


## Домашнее задание 2. Очередь на массиве

Модификации
 * *Базовая*
    * Классы должны находиться в пакете `queue`
    * [Исходный код тестов](tests/java/queue/ArrayQueueTest.java)
    * [Откомпилированные тесты](tests/artifacts/queue/ArrayQueueTest.jar)
 * *ToArray* (32-33)
    * Реализовать метод `toArray`, возвращающий массив,
      содержащий элементы, лежащие в очереди в порядке
      от головы к хвосту.
    * Исходная очередь должна остаться неизменной
    * Дублирования кода быть не должно
    * [Исходный код тестов](tests/java/queue/ArrayQueueToArrayTest.java)
    * [Откомпилированные тесты](tests/artifacts/queue/ArrayQueueToArrayTest.jar)
 * *Indexed* (34-35)
    * Реализовать методы
        * `get` – получить элемент по индексу, отсчитываемому с головы
        * `set` – заменить элемент по индексу, отсчитываемому с головы
    * [Исходный код тестов](tests/java/queue/ArrayQueueIndexedTest.java)
    * [Откомпилированные тесты](tests/artifacts/queue/ArrayQueueIndexedTest.jar)
 * *Deque* (36-37)
    * Реализовать методы
        * `push` – добавить элемент в начало очереди
        * `peek` – вернуть последний элемент в очереди
        * `remove` – вернуть и удалить последний элемент из очереди
    * [Исходный код тестов](tests/java/queue/ArrayDequeTest.java)
    * [Откомпилированные тесты](tests/artifacts/queue/ArrayDequeTest.jar)
 * *DequeToStrArray* (38-39)
    * Реализовать модификацию *Deque*
    * Реализовать метод `toArray`, возвращающий массив,
      содержащий элементы, лежащие в очереди в порядке
      от головы к хвосту.
    * Реализовать метод `toStr`, возвращающий строковое представление
      очереди в виде '`[`' _голова_ '`, `' ... '`, `' _хвост_ '`]`'
    * [Исходный код тестов](tests/java/queue/ArrayDequeToStrArrayTest.java)
    * [Откомпилированные тесты](tests/artifacts/queue/ArrayDequeToStrArrayTest.jar)


## Домашнее задание 1. Бинарный поиск

Модификации
 * *Базовая*
    * Класс `BinarySearch` должен находиться в пакете `search`
    * [Исходный код тестов](tests/java/search/BinarySearchTest.java)
    * [Откомпилированные тесты](tests/artifacts/search/BinarySearchTest.jar)
 * *Missing* (32-33)
    * Если в массиве `a` отсутствует элемент, равный `x`, то требуется
      вывести индекс вставки в формате, определенном в
      [`Arrays.binarySearch`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Arrays.html#binarySearch(int%5B%5D,int)).
    * Класс должен иметь имя `BinarySearchMissing`
    * [Исходный код тестов](tests/java/search/BinarySearchMissingTest.java)
    * [Откомпилированные тесты](tests/artifacts/search/BinarySearchMissingTest.jar)
 * *Min* (34-35)
    * На вход подается циклический сдвиг 
      отсортированного (строго) по возрастанию массива.
      Требуется найти в нем минимальное значение.
    * Класс должен иметь имя `BinarySearchMin`
    * [Исходный код тестов](tests/java/search/BinarySearchMinTest.java)
    * [Откомпилированные тесты](tests/artifacts/search/BinarySearchMinTest.jar)
 * *Span* (36-37)
    * Требуется вывести два числа: начало и длину диапазона элементов,
      равных `x`. Если таких элементов нет, то следует вывести
      пустой диапазон, у которого левая граница совпадает с местом
      вставки элемента `x`.
    * Не допускается использование типов `long` и `BigInteger`.
    * Класс должен иметь имя `BinarySearchSpan`
    * [Исходный код тестов](tests/java/search/BinarySearchSpanTest.java)
    * [Откомпилированные тесты](tests/artifacts/search/BinarySearchSpanTest.jar)
 * *Max* (38-39)
    * На вход подается массив полученный приписыванием 
      отсортированного (строго) по убыванию массива 
      в конец массива отсортированного (строго) по возрастанию
      Требуется найти в нем максимальное значение.
    * Класс должен иметь имя `BinarySearchMax`
    * [Исходный код тестов](tests/java/search/BinarySearchMaxTest.java)
    * [Откомпилированные тесты](tests/artifacts/search/BinarySearchMaxTest.jar)
