-- Passar de "12 + 3 x 5" para [TokenInt 12, TokenPlus, TokenInt 3, TokenTimes, TokenInt3]
import Data.Char
data Token = TokenInt Int
           | TokenPlus
           | TokenTimes
           deriving Show

lexer :: String -> [Token]
lexer [] = []
lexer (x:xs)
        | isDigit x = (lexNum(x:xs))
        | isSpace x = (lexer (xs))
        | x == '+'  = (TokenPlus) : (lexer (xs))
        | x == '*'  = (TokenTimes) : (lexer (xs))

lexNum :: String -> [Token]
lexNum s = let (l, r) = span isDigit s
               in TokenInt(read(l)) : lexer r
               