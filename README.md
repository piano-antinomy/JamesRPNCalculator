# JamesRPNCalculator
RPN Calculator application built by James

## How to build & run this calculator using command line: 

build: 
```./gradlew build```

execute: 
```./gradlew run --console=plain```

example:

```

Jamess-MacBook-Pro:JamesRPNCalculator jamesding$ ./gradlew run --console=plain
> Task :compileJava UP-TO-DATE
> Task :processResources NO-SOURCE
> Task :classes UP-TO-DATE

> Task :run

Welcome to Rpn Calculator, start input below: 
5 2
stack: 5 2
2 sqrt
stack: 5 2 1.4142135623
clear
stack: 

9 sqrt
stack: 3
clear
stack: 
5 2 -
stack: 3
3 -
stack: 0
clear
stack: 

5 4 3 2
stack: 5 4 3 2
undo undo *
stack: 20
5 *
stack: 100
undo
stack: 20 5
clear
stack: 

7 12 2 /
stack: 7 6
*
stack: 42
4 /
stack: 10.5
clear
stack: 

1 2 3 4 5
stack: 1 2 3 4 5
* 
stack: 1 2 3 20
clear 3 4 -
stack: -1
clear
stack: 

1 2 3 4 5
stack: 1 2 3 4 5
* * * *
stack: 120
clear 
stack: 
1 2 3 * 5 + * * 6 5
operator * (position: 15): insufficient parameters
stack: 11

```
