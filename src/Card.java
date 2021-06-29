public class Card {
    private String type;
    private String value;

    Card(String type, String value){
        this.type = type;
        this.value = value;
    }

    public String getType(){
        return type;
    }

    public String getValue(){
        return value;
    }

    public boolean equals(Card c){
        return this.type.equals(c.getType()) && this.value.equals(c.getValue());
    }

}
