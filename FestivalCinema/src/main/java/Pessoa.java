public class Pessoa {
    private String primeiroNome;
    private String ultimoNome;
    private String genero;
    
    public Pessoa(String primeiroNome, String ultimoNome, String genero){
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.genero = genero;
    }
    
    public String getPrimeiroNome(){
        return primeiroNome;
    }
     public String getUltimoNome(){
        return ultimoNome;
    }
      public String getGenero(){
        return genero;
    }

}
