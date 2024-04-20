package Website;

import io.restassured.RestAssured;

public class Jira_post {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://stock-market-123.atlassian.net/rest/api/2";
        RestAssured.given()
                .header("Authorization","Basic bXV0dHVyYWphMTk5NkBnbWFpbC5jb206QVRBVFQzeEZmR0YwZE5FZU0yNEhpQ1VhU1RScHZRaEVuN0JxZzRsZ3JZX3VxRHN3d0ItRzdKaTR5VlRyeDVzcjZMVHBreWlISHJldTRGTUEwb1c1MHNLYkctZU1hMXRrb1NqQm0tYjdrUndBRlF1Z00tNHY3OVQ1emNpQTZyV2JZQ3B1OWdweHF6OFpzZWRsckxoVHlIcTFCZG1Fd3lLSzBGSEI5anpocTdNQlZKVWNoMHN3YUpZPTU5MEI5ODk2")
                .header("Content-Type","application/json").body("{\n" +
                        "    \"fields\": {\n" +
                        "      \"project\": {\n" +
                        "        \"key\": \"STOC\"\n" +
                        "      },\n" +
                        "      \"summary\": \"Needs to change the length of the alpha\",\n" +
                        "      \"description\": \"Added Screnshot\",\n" +
                        "      \"issuetype\": {\n" +
                        "        \"name\": \"Task\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }")
                .when().post("issue").then().assertThat().statusCode(201).log().all();



    }


}
