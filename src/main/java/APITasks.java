
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Assert;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by nurkulov 12/26/19
 */
public class APITasks {

    /*
     * GET all soccer team names listed in given resource
     * Deserialization type: TypeReference
     */
    public static List<String> getAllTeams() throws IOException, URISyntaxException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.football-data.org").setPath("v2/teams");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.addHeader("accept","application/json");
        httpGet.addHeader("X-Auth-Token","a91af6e8144e4ddca37e290aa57ea114");

        HttpResponse response = httpClient.execute(httpGet);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        Assert.assertTrue(response.getEntity().getContentType().getValue().contains("application/json"));

        ObjectMapper objectMapper =new ObjectMapper();
        Map<String,Object> deseializedMap = objectMapper.readValue(response.getEntity().getContent(),new TypeReference<Map<String,Object>>() {
        });

        List<String> allTeams = new ArrayList<>();
        List<Map<String,Object>> listOfTeams = (List<Map<String,Object>>) deseializedMap.get("teams");
        for (int i =0; i<listOfTeams.size(); i++){
            Map<String,Object> map = listOfTeams.get(i);
            allTeams.add((String) map.get("name"));


        }

        return allTeams;

    }



    /*
     * GET names of all goalkeepers from England team
     * note: England team id is 66
     * Deserialization type: TypeReference
     */
    public static List<String> getAllGoalkeepers() throws URISyntaxException, IOException {
        List<String> allGoalkeepers = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.football-data.org").setPath("v2/teams/66");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("accept","application/json");
        httpGet.addHeader("X-Auth-Token","a91af6e8144e4ddca37e290aa57ea114");

        HttpResponse response = client.execute(httpGet);


        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        Assert.assertTrue(response.getEntity().getContentType().getValue().contains("application/json"));

        Map<String,Object> parsedResponse = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String,Object>>() {
        });

        List<Map<String,Object>> squadGoalkeepers = (List<Map<String,Object>>) parsedResponse.get("squad");
        for(Map<String,Object> eachPlayer : squadGoalkeepers){

            if(eachPlayer.get("position").equals("Goalkeeper")){
                allGoalkeepers.add(eachPlayer.get("name").toString());
            }

        }

        return allGoalkeepers;
    }

    /*
     * GET names of all defenders from England team
     * note: England team id is 66
     * Deserialization type: TypeReference
     */

        public static List<String> getDefenders() throws URISyntaxException, IOException {
            HttpClient httpClient = HttpClientBuilder.create().build();
            URIBuilder uriBuilder = new URIBuilder();
            uriBuilder.setScheme("https").setHost("api.football-data.org").setPath("v2/teams/66");
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.addHeader("accept","application/json");
            httpGet.addHeader("X-Auth-Token","a91af6e8144e4ddca37e290aa57ea114");
            HttpResponse response = httpClient.execute(httpGet);
            Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
            Assert.assertTrue(response.getEntity().getContentType().getValue().contains("application/json"));
            ObjectMapper objectMapper =new ObjectMapper();
            Map<String,Object> deseializedMap = objectMapper.readValue(response.getEntity().getContent(),new TypeReference<Map<String,Object>>() {
            });
            List<String> defenders = new ArrayList<>();
            List<Map<String,Object>> listOfSquad =(List<Map<String,Object>>) deseializedMap.get("squad");
            for (int i =0; i<listOfSquad.size(); i++){
                Map<String,Object> map = listOfSquad.get(i);
                if (((String)map.get("position")).equals("Defender")){
                    defenders.add((String) map.get("name"));



                }
            }
            return defenders;

    }

    /*
     * GET names of all midfielders from England team
     * note: England team id is 66
     * Deserialization type: TypeReference
     */
    public static List<String> getMidfielders() throws URISyntaxException, IOException {
        List<String> allMidfielders = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.football-data.org").setPath("v2/teams/66");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("accept","application/json");
        httpGet.addHeader("X-Auth-Token","a91af6e8144e4ddca37e290aa57ea114");

        HttpResponse response = client.execute(httpGet);


        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        Assert.assertTrue(response.getEntity().getContentType().getValue().contains("application/json"));

        Map<String,Object> parsedResponse = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String,Object>>() {
        });

        List<Map<String,Object>> squadGoalkeepers = (List<Map<String,Object>>) parsedResponse.get("squad");
        for(Map<String,Object> eachPlayer : squadGoalkeepers){

            if(eachPlayer.get("position").equals("Midfielder")){
                allMidfielders.add(eachPlayer.get("name").toString());
            }

        }

        return allMidfielders;
    }

    /*
     * GET names of all midfielders from England team whose country is Brazil
     * note: England team id is 66
     * Deserialization type: TypeReference
     */
    public static List<String> getMidfielderFromBrazil() throws URISyntaxException, IOException {
        List<String> allMidfieldersBrazil = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.football-data.org").setPath("v2/teams/66");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("accept","application/json");
        httpGet.addHeader("X-Auth-Token","a91af6e8144e4ddca37e290aa57ea114");

        HttpResponse response = client.execute(httpGet);


        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        Assert.assertTrue(response.getEntity().getContentType().getValue().contains("application/json"));

        Map<String,Object> parsedResponse = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String,Object>>() {
        });

        List<Map<String,Object>> squadGoalkeepers = (List<Map<String,Object>>) parsedResponse.get("squad");
        for(Map<String,Object> eachPlayer : squadGoalkeepers){

            if(eachPlayer.get("position").equals("Midfielder") && eachPlayer.get("countryOfBirth").equals("Brazil")){
                allMidfieldersBrazil.add(eachPlayer.get("name").toString());
            }

        }


        return allMidfieldersBrazil;
    }

    /*
     * GET names of all attackers from England team whose origin country is England
     * note: England team id is 66
     * Deserialization type: TypeReference
     */
    public static List<String> getAttackerFromEngland() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.football-data.org").setPath("v2/teams/66");
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("accept","application/json");
        httpGet.addHeader("X-Auth-Token","a91af6e8144e4ddca37e290aa57ea114");
        HttpResponse response = httpClient.execute(httpGet);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        Assert.assertTrue(response.getEntity().getContentType().getValue().contains("application/json"));
        ObjectMapper objectMapper =new ObjectMapper();
        Map<String,Object> deseializedMap = objectMapper.readValue(response.getEntity().getContent(),new TypeReference<Map<String,Object>>() {
        });
        List<String> attackers = new ArrayList<>();
        List<Map<String,Object>> listOfSquad =(List<Map<String,Object>>) deseializedMap.get("squad");
        for (int i =0; i<listOfSquad.size(); i++){
            Map<String,Object> map = listOfSquad.get(i);
            if (((String)map.get("position")).equals("Attacker")&& ((String) map.get("countryOfBirth")).equals("England")){
                attackers.add((String) map.get("name"));
            }
        }
        return attackers;

    }

    /*
     * GET name of Spain team coach
     * note: Spain team id is 77
     * Deserialization type: TypeReference
     */
    public static List<String> getSpainCoach() throws URISyntaxException, IOException {
        List<String> allSpainCoach = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.football-data.org").setPath("v2/teams/77");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("accept","application/json");
        httpGet.addHeader("X-Auth-Token","a91af6e8144e4ddca37e290aa57ea114");

        HttpResponse response = client.execute(httpGet);


        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        Assert.assertTrue(response.getEntity().getContentType().getValue().contains("application/json"));

        Map<String,Object> parsedResponse = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String,Object>>() {
        });

        List<Map<String,Object>> squadGoalkeepers = (List<Map<String,Object>>) parsedResponse.get("squad");
        for(Map<String,Object> eachPlayer : squadGoalkeepers){

            if(eachPlayer.get("role").equals("COACH")){
                allSpainCoach.add(eachPlayer.get("name").toString());
            }

        }


        return allSpainCoach;
    }

    /*
     * GET list of all competitions
     * Deserialization type: TypeReference
     */
    public static List<String> getAllCompetitions() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.football-data.org").setPath("v2/competitions");
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("accept", "application/json");
        httpGet.addHeader("X-Auth-Token", "a91af6e8144e4ddca37e290aa57ea114");
        HttpResponse response = httpClient.execute(httpGet);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        Assert.assertTrue(response.getEntity().getContentType().getValue().contains("application/json"));
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> deseializedMap = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String, Object>>() {
        });
        List<String> competitions = new ArrayList<>();
        List<Map<String, Object>> listOfCompetitions = (List<Map<String, Object>>) deseializedMap.get("competitions");
        for (int i = 0; i < listOfCompetitions.size(); i++) {
            Map<String, Object> map = listOfCompetitions.get(i);
            competitions.add((String) map.get("name"));
        }
        return competitions;
    }

    /*
     * GET names of second highest scorrer from competitions of 2000 season
     * note: endpoint for competitions: `competitions/<year>/
     * note: endpoint for scorers: `competitions/<year>/scorers`
     * Deserialization type: TypeReference
     */
    public static List<String> getSecondHighestScorer() throws URISyntaxException, IOException {

        List<String> secondHighestScore = new ArrayList<>();
        //http://api.football-data.org/v2/competitions/2000/scorers
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.football-data.org").setPath("v2/competitions/2000/scorers");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("accept","application/json");
        httpGet.addHeader("X-Auth-Token","a91af6e8144e4ddca37e290aa57ea114");

        HttpResponse response = client.execute(httpGet);

        Map<String,Object> parsedResponse = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String,Object>>() {
        });

        List<Map<String,Object>> scoreresList = (List<Map<String,Object>>) parsedResponse.get("scorers");
        List<Integer> allNumberOfGoals = new ArrayList<>();

        for(Map<String,Object> allPlayers : scoreresList ){
            allNumberOfGoals.add((Integer)allPlayers.get("numberOfGoals"));

        }


        Collections.sort(allNumberOfGoals);
        int biggestGoalNumber = allNumberOfGoals.get(allNumberOfGoals.size()-1);
        int secondHighestGoalNumber = 0;
        for (int i = allNumberOfGoals.size()-1;i >0; i--) {
           if(allNumberOfGoals.get(i)<biggestGoalNumber){
               secondHighestGoalNumber=allNumberOfGoals.get(i);
               break;
           }
        }


        for(Map<String,Object> scorers : scoreresList){
            Map<String,Object> eachPlayer = (Map<String,Object>) scorers.get("player");

            if((Integer)scorers.get("numberOfGoals")==(secondHighestGoalNumber)){
                secondHighestScore.add(eachPlayer.get("name").toString());
            }

        }

        return secondHighestScore;
    }
}
