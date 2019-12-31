{-1)
a)  [2,3,1,4,4]
b)  [0,10,20,30,40]
c)  [[],[3,4],[5]]
d)  5
e)  [1,1,1,1,1,1]
f)  [(1,4),(2,3),(3,2)]
g)  [2^x | x<-[0..6]]
h)  0
i)  [(Bool, Int)]
j)  troca :: (a,b) -> (b,a)
k)  g :: Int -> Int -> Int
l)  [([Int], Int)] 
-}
--2)
--a
{-ttriangulo :: Num a => a -> a -> a -> String
ttriangulo x y z | x == y && x == z = "equilátero"
                 | x /= y && x /= z && y /= z = "escaleno"
                 | otherwise = "isósceles"
--b
rectangulo :: Num a => a -> a -> a -> Bool
rectangulo x y z = x^2 == (y^2 + z^2)

--3)
maiores :: [Int] -> [Int]
maiores [] = []
maiores [x] = []
maiores (x:y:xs) = if(x>y) then x: maiores (y:xs) else maiores(y:xs)

--4
somapares :: [(Int, Int)] -> [Int]
somapares [] = []
somapares (x:xs) = x+y : somapares xs-}

h[] = 1
h[x] = x
h(x:y:xs) = x*y + h (y:xs)

f x xs = sum xs < x

ig :: [Int] -> Bool
ig [] = True
ig [x] = True
ig (x1:x2:xs) = x1 == x2 && ig (x2:xs)

fix f x = f x == x

zip3 xs yz zs = [(x,y,z) | l<-[0..k], x<-xs!!l, y<-ys!!l, z<-zs!!l]
              where k = minimum( minimum( length xs, length ys) length zs)