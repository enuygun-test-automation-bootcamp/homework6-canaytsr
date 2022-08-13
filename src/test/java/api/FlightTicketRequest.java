package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.restassured.response.Response;
import model.Root;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;



public class FlightTicketRequest {

    Response response;
    public List<String> flightTicketFromList(String kw) throws UnirestException, IOException {
        HttpResponse<JsonNode> response = Unirest.get("https://www.enuygun.com/ucak-bileti/trip-autocomplete/?term="+kw)
                .header("cookie", "SERVERID-SH=shwww7; SERVERID-SAG=rdwww10")
                .asJson();
        ObjectMapper mapper = new ObjectMapper();
        Root[] flightList = mapper.readValue(response.getBody().toString(), Root[].class);

        //Converting array to list
        List<?> array_list = Arrays.stream(flightList).collect(Collectors.toList());

        List<String> city_name_code_airport_country2 = new ArrayList<>();

        //assing country code list for using array_list elements
        for(int i=0;i<array_list.size();i++) {
            city_name_code_airport_country2.add(
                    array_list.get(i).toString()+
                     array_list.get(i+1).toString()+
                     array_list.get(i+2).toString());
        }

        List<String> city_name_code_airport_country = new ArrayList<>();

        for(int i=0;i<flightList.length;i++){
            city_name_code_airport_country.add
                    (flightList[i].getCity_code()+","+
                            flightList[i].getCountry_name()+":"+
                            flightList[i].getAirport()+":"+
                            flightList[i].getCity_name()
                    );
        }
        return city_name_code_airport_country;

    }
    //This method convert to parametric method
    public void flightTiketFromListByRest(String kw){

        response = given()
                .header("content-type","application/json")
                .when()
                .get("https://www.enuygun.com/ucak-bileti/trip-autocomplete/?term="+kw)
                .then()
                .statusCode(200)
                .extract().response();

        // Creating a Gson Object
        Gson gson = new Gson();
        //Convert the response to  java object
        Root root = gson.fromJson((Reader) response,Root.class);




    }
}
