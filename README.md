# CSYE 6200 Final Project
> Authors: Seamus McAvoy, Vidhi Patel

Interpreter for a popular esoteric language with an offensive name (see <a href= "https://esolangs.org/wiki/Brainfuck">here</a>).

The language operates on an array of memory cells, initially set to 0. The following commands are supported:
- `>`: Move the pointer one cell to the right
- `<`: Move the pointer one cell to the left
- `+`: Increment the current memory cell by 1
- `-`: Decrement the current memory cell by 1
- `.`: Output the character signified by the current memory cell
- `,`: Input a character and store it in the current memory cell
- `[`: Jump past the matching ']' if the current memory cell is 0
- `]`: Jump back to the matching '[' if the current memory cell is 0
- All other characters are considered comments and ignored.

Hello World:
```
++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.
```