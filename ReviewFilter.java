import java.io.*;
import java.util.*;

public class ReviewFilter {
    private HashMap<String, List<OnlineReview>> userMap;
    private HashMap<String, HashMap<Integer, List<OnlineReview>>> productStarMap;

    private ReviewFilter(String filePath){
        userMap = null;
        productStarMap = null;
        List<OnlineReview> reviews = loadReviews(filePath);
        loadMaps(reviews);
    }
    private List<OnlineReview> loadReviews(String filePath) {
        List<OnlineReview> reviews;
        reviews = null;
        try {
            BufferedReader input = new BufferedReader(new FileReader(filePath));
            String currLine;
            while ((currLine = input.readLine()) != null) {
                String[] reviewInfo = currLine.split(";");
                String username = reviewInfo[0];
                String product = reviewInfo[1];
                int stars = Integer.parseInt(reviewInfo[2]);
                String comments = reviewInfo[3];

                OnlineReview currReview = new OnlineReview(username, product, stars, comments);
                reviews.add(currReview);
            }
            input.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }

    private void loadMaps(List<OnlineReview> reviews){
        for(OnlineReview currReview : reviews){
            String user = currReview.getUsername();
            String product = currReview.getProduct();
            int stars = currReview.getStars();

            if(!userMap.containsKey(user)){
                List<OnlineReview> reviewsList = new ArrayList<>();
                reviewsList.add(currReview);
                userMap.put(user,reviewsList);
            }
            else{
                List<OnlineReview> reviewsList = userMap.get(user);
                reviewsList.add(currReview);
                userMap.put(user,reviewsList);
            }

            if(!productStarMap.containsKey(product)){
                HashMap<Integer, List<OnlineReview>> rating = new HashMap<>();
                List<OnlineReview> review = new ArrayList<>();
                review.add(currReview);

                rating.put(stars,review);
                productStarMap.put(product, rating);
            }
            else{
                HashMap<Integer, List<OnlineReview>> rating = productStarMap.get(product);
                List<OnlineReview> list = rating.get(stars);
                list.add(currReview);
                rating.put(stars,list);

                productStarMap.put(product,rating);
            }

        }
    }

    public void getUserReview(String username) throws Exception{
        List<OnlineReview> myList = userMap.get(username);
        for(OnlineReview current : myList){
            System.out.println(current.toString());
        }
    }

    public void getProductStarReviews(String product, int stars) throws Exception{
        HashMap<Integer, List<OnlineReview>> starMap = productStarMap.get(product);
        List<OnlineReview> reviews = starMap.get(stars);
        for(OnlineReview current : reviews){
            System.out.println(current.toString());
        }
    }
}