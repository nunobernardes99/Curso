-- Exercício 1
inc x = x + 1
dobro x = x + x
quadrado x = x * x
media x y = (x + y)/2

-- Exercício 2
triangulo a b c = if(a<b+c && b<a+c && c<a+b) then True else False
-- ou
trianguloV2 a b c = a<b+c && b<a+c && c<a+b

-- Exercício 3
heron a b c = sqrt(s*(s-a)*(s-b)*(s-c))
			where s = (a+b+c)/2

-- Exercício 4
metades xs = (take n xs,drop n xs)
		   where n = (length xs)`div`2

-- Exercício 5
-- a)
lastEdited xs = xs !! s
			  where s = (length xs)-1
-- b)
initV1 xs = take s xs
		  where s = (length xs)-1
initV2 xs =  reverse (tail (reverse xs))

-- Exercício 6
-- a)
binom n k = (product[1..n])`div`(product[1..k]*product[1..s])
		  where s = n-k

-- b) ??
productV1 xs ys = product xs * product ys
productV2 xs ys = product(xs++ys)

-- Exercício 7
-- a)
max3 a b c | a>=b && a>=c = a
		   | b>=a && b>=c = b
		   | c>=a && c>=b = c

min3 a b c | a<=b && a<=c = a
		   | b<=a && b<=c = b
		   | c<=a && c<=b = c
-- b)
max4 a b c = if(a>=b && a>=c) then a else (if(b>=a && b>=c) then b else c)
min4 a b c = if(a<=b && a<=c) then a else (if(b<=a && b<=c) then b else c)

-- Exercício 8
-- a)
maxOccurs :: Integer -> Integer -> (Integer, Integer)
maxOccurs a b | a==b = (a,2)
			  | a>b = (a,1)
			  | b>a = (b,1)

-- b)
orderTriple :: (Integer, Integer, Integer) -> (Integer, Integer, Integer)
orderTriple (a,b,c) | a>=b && a>=c && b>=c = (a,b,c)
					| a>=c && a>=b && c>=b = (a,c,b)
					| b>=a && b>=c && a>=c = (b,a,c)
					| b>=c && b>=a && c>=a = (b,c,a)
					| c>=a && c>=b && a>=b = (c,a,b)
					| c>=b && c>=a && b>=a = (c,b,a)


-- Exercício 9
classifica :: Int -> String
classifica x | x<=9 = "Reprovado"
			 | x>=10 && x<=12 = "Suficiente"
			 | x>=12 && x<=15 = "Bom"
			 | x>=16 && x<=18 = "Muito bom"
			 | x>=19 && x<=20 = "Muito bom com distinção"

-- Exercício 10
xor :: Bool -> Bool -> Bool
xor True True   = False
xor True False  = True
xor False True  = True
xor False False = False

-- Exercício 11
safetailV1 :: Eq a => [a] -> [a]
safetailV1 xs = if(xs == []) then [] else tail(xs)

safetailV2 :: Eq a => [a] -> [a]
safetailV2 xs | xs == [] = []
			  | otherwise = tail xs

safetailV3 :: [a] -> [a]
safetailV3 [] = []
safetailV3 xs = tail xs

-- Exercício 12 (curtaV2 correta?)
curtaV1 :: [a] -> Bool
curtaV1 xs = length xs <= 2
-- Se nesta fizermos um input curtaV1 [1..] a função nunca vai parar, enquanto que na V2 para.

curtaV2 :: [a] -> Bool
curtaV2 []      = True
curtaV2 [_]     = True
curtaV2 [_,_]   = True  -- Os valores que a lista guarda não interessam, por isso pômos '_'
curtaV2 _       = False -- Qualquer outro valor diferente das condições acima

-- Exercício 22
soma :: Int
soma = sum([x^2 |x <- [1..100]])

-- Exercício 23
-- a)
aprox :: Int -> Double
aprox n = 4*sum([(-1)^x/fromIntegral(2*x+1) | x<-[0..n]])

-- b)
aprox' :: Int -> Double
aprox' n = sqrt(12*sum([(-1)^x/fromIntegral((x+1)^2)| x<-[0..n]]))

-- Exercício 24
divprop :: Int -> [Int]
divprop n = [ x | x<-[1..n-1], n`mod`x == 0 ]

-- Exercício 25
perfeitos :: Int -> [Int]
perfeitos n = [ x | x<-[1..n], sum(divprop x) == x ]

-- Exercício 26
primo :: Int -> Bool
primo n = length(divprop n) == 1

-- Exercício 27
pascal :: Int -> [[Int]]
pascal n = [[binom y x] | y<-[1..n], x<-[1..y]]

-- Exercício 28
-- dotprod :: [Float] -> [Float] -> Float
-- dotprod xs ys = product(zip xs ys) 

-- Exercício 30
-- a
factorial' :: Int -> Int
factorial' 1 = 1
factorial' n = n * factorial' (n-1)

-- b
rangeProduct :: Int -> Int -> Int
rangeProduct m n | m == n    = n
				 | otherwise = rangeProduct m (n-1) * n

-- Exercício 31
multiply :: Int -> Int -> Int
multiply x 1 = x
multiply x y = sum [x] + multiply x (y-1)

-- Exercício 32
intSquare :: Int -> Int
intSquare n = intSquareAux n n
intSquareAux k n | k^2 <= n = k
				 | otherwise = intSquareAux (k-1) n

-- Exercício 33
maxFun :: (Integer -> Integer) -> Integer -> Integer 
maxFun f y | y == 0    = 0
		   | otherwise = maximum[f y, maxFun f (y-1)]



-- Exercício 36
mdc :: (Int,Int) -> Int
mdc (a,b) | b == 0    = a
		| otherwise = mdc(b, a`mod`b)

-- Exercício 37
powOfTwo :: Int -> Int
powOfTwo 0 = 1
powOfTwo n = 2 * powOfTwo(n-1)

-- Exercício 38
-- a
and' :: [Bool] -> Bool
and' [] = True
and' (x:xs) = x && and' xs

-- b
or' :: [Bool] -> Bool
or' [] = False
or' (x:xs) = x || or' xs

-- c
concat' :: [[a]] -> [a]
concat' [] = []
concat' (x:xs) = x ++ concat' xs

-- d
replicate' :: Int -> a -> [a]
replicate' 0 a = []
replicate' n a = [a] ++ replicate' (n-1) a

-- e
(!!!) :: [a] -> Int -> a
(!!!) (x:xs) 0 = x
(!!!) (x:xs) a = (!!!) xs (a-1)

-- f
elem' :: Eq a => a -> [a] -> Bool
elem' y [] = False
elem' y (x:xs) = if (y == x) then True else elem' y xs

-- Exercício 40
--forte :: String -> Bool
--forte xs = length xs >= 8 && length[c | c <- xs, isLower c] > 0 && length [c | c <- xs, isUpper c] > 0 && length [c | c <- xs, isDigit c] > 0

-- Exercício 42
intersperse :: a -> [a] -> [a]
intersperse _ [] = []
intersperse _ [x] = [x]
intersperse sep (x:xs) = x : sep : intersperse sep xs


-- e618
-- 1
-- a)  "abc" : [[ ]] ++ "dce" : [] 								= ["abc", "", "dce"]
-- b)  tail ([1]:[2]:[]:[3]:[4]:[]) 							= [[2],[],[3],[4]]
-- c) [[1,2,3,4],[5,6,7,8],[9,10,11,12]] !! 2 !! 1 				= 10
-- d) map (\x -> x * 2) [1,2,3,4] 								= [2,4,6,8]
-- e) zipWith (-) [1,3..10] [0,3..] 							= [1,3,5,7,9] - [0,3,6,9,12] = [1,0,-1,-2,-3]
-- f) dropWhile (<6) [2..10] 									= [6,7,8,9,10]
-- g) [x | x <- [1..10], x <= 6, x >= 4] 						= [4,5,6]
-- h) [-2,4,-8,16,-32,64,-128,256,...] 							= [(-2)^n| n<-[1..5]]
-- i) h = let f [x] = 1
--			  f (y:z) = y * f z
--		  in f [1,2,3,4,5]										= 24
-- j) f xs = head (tail xs)										= f :: [a] -> a
-- k) ([False, True], ['0', '1'])								= ([False, True], ['0', '1']) :: ([Boolean], [Char])
-- l) data E a = V a | Op (E a) (E a)
--	  aval (V v) f g = f v
--	  aval (Op e1 e2) f g = g (aval e1 f g) (aval e2 f g)	e	= aval :: E a -> (a -> Bool) -> (Bool -> Bool -> Bool) -> Bool
-- m) foldr (++) [] :: [[a]] -> [a]

-- a
aprov :: [Int] -> [Char]
aprov [] = []
aprov [x] = if(x < 15) then ['R'] else ['A']
aprov (x:xs) = if(x < 15) then 'R' : aprov xs else 'A' : aprov xs

-- b
injust :: [Int] -> Int
injust [] = 0
injust [x] = if(x >= 10 && x < 15) then 1 else 0
injust (x:xs) = if(x >= 10 && x < 15) then 1 + injust xs else injust xs

-- 3
repete :: a -> [[a]]
repete a = [[]] ++ [repeteAux 0 a]

repeteAux :: Int -> a -> [[a]]
repeteAux i a = [a | b <- [0..i]] ++ [repeteAux (i+1) a]

-- 5
-- a
compLr :: [a -> a] -> a -> a
compLr [] v = v
compLr (f:fs) v = f (compLr fs v)

-- b
compL :: [a -> a] -> a -> a
compL fs v = foldr f v fs
		where f g x = g x
		
-- 6
-- a
data Arv a = Vazia | No a (Arv a) (Arv a)
soma :: Num a => Arv a -> a
soma Vazia = 0
soma (No x esq dir) = x + (soma esq) + (soma dir)
-- b
foldrtree :: (a -> b -> b -> b) -> b -> Arv a -> b
foldrtree f v Vazia = v
foldrtree f v (No x esq dir) = f x (foldrtree f v esq) (foldrtree f v dir)


