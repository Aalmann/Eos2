//count 1 to 10
data
i: 0

code  
  LOADI 10
  STORE i
  JMP cond
body:
  LOAD i
  INC      
  STORE i
cond: 
  LOAD i
  CMP 0
  JMPNE body
  HLT  