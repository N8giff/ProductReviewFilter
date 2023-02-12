public class OnlineReview {
    private String username;
    private String product;
    private int stars;
    private String comments;

    public OnlineReview(String username, String product, int stars, String comments){
        this.username = username;
        this.product = product;
        this.stars = stars;
        this. comments = comments;
    }

    public String getUsername(){
        return username;
    }

    public String getProduct(){
        return product;
    }

    public int getStars(){
        return stars;
    }

    public String toString(){
        String st = username + " - Product '" + product + "' - " + stars + " Stars: '" + comments + "'";
        return st;
    }

    public static void main(String[] args) {
        OnlineReview current = new OnlineReview("N8Giff", "Water Bottle", 5, "Terrible. Aweful. Hate it.");
        System.out.println(current.toString());
    }
}
