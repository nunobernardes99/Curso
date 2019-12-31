data Exp = Num Int
         | ExpOp Op Exp Exp
     deriving(Show, Eq)

data Op = ADD | MULT
     deriving(Show, Eq)

interp :: Exp -> Int
interp (Num n)            = n
interp (ExpOp ADD e1 e2)  = (interp e1) + (interp e2)
interp (ExpOp MULT e1 e2) = (interp e1) * (interp e2)

-- FIM DA AULA 1


data Instr = Push Int
           | ADD2
           | MULT2
           deriving (Eq, Show)

type Code = [Instr]

compile :: Exp -> Code
compile (Num n) = [Push n]
compile (ExpOp ADD e1 e2)  = (compile e1) ++ (compile e2) ++ [ADD2]
compile (ExpOp MULT e1 e2) = (compile e1) ++ (compile e2) ++ [MULT2]

type Stack = [Int]
run :: Code -> Int
run c = runState([], c)

runState :: (Stack, Code) -> Int
runState (x:s, []) = x
runState (s, c:r)  = runState(step(s, c:r))

step :: (Stack, [Instr]) -> (Stack, Code)
step (s, (Push n):r)  = (n:s, r)
step (x:y:s, ADD2:r)  = ((x+y):s, r)
step (x:y:s, MULT2:r) = ((x*y):s, r)

