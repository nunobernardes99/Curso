data Exp = Num Int
         | ExpOp Op Exp Exp
     deriving(Show, Eq)

data Op = ADD | MULT
     deriving(Show, Eq)

interp :: Exp -> Int
interp (Num n)            = n
interp (ExpOp ADD e1 e2)  = (interp e1) + (interp e2)
interp (ExpOp MULT e1 e2) = (interp e1) * (interp e2)
