class Numbers {
    public static void main(String[] args) {
        int n=500;
        int soma =0;
        System.out.println("Numeros de 1 a " + n + ": ");
        for(int i=1; i<=n; i++) {
        	soma += i;
            //System.out.println(i);
        }
        System.out.println(soma);
    }
}