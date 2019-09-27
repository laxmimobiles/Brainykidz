package mycom.example.harshu.janavi;

public class Words {
    private String word1,word2,word3;
    public Words(String word1,String word2,String word3){
        this.word1=word1;
        this.word2=word2;
        this.word3=word3;

    }
    public Words(){
        super();
    }
    public void setWord1(String word1){
        this.word1=word1;
    }
    public Words(String word1){
        this.word1=word1;


    }
    public Words(String word2,String word3){
        this.word2=word2;
        this.word3=word3;

    }
    public String getWord1(){
        return word1;
    }
    public String getWord2(){
        return word2;
    }
    public String getWord3(){
        return word3;
    }

}
