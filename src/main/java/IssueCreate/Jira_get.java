package IssueCreate;

import io.restassured.RestAssured;

import java.util.List;

public class Jira_get {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://stock-market-123.atlassian.net/rest/api/2";

        List<String> shs = RestAssured.given()
                .header("Authorization", "Basic bXV0dHVyYWphMTk5NkBnbWFpbC5jb206QVRBVFQzeEZmR0YwZE5FZU0yNEhpQ1VhU1RScHZRaEVuN0JxZzRsZ3JZX3VxRHN3d0ItRzdKaTR5VlRyeDVzcjZMVHBreWlISHJldTRGTUEwb1c1MHNLYkctZU1hMXRrb1NqQm0tYjdrUndBRlF1Z00tNHY3OVQ1emNpQTZyV2JZQ3B1OWdweHF6OFpzZWRsckxoVHlIcTFCZG1Fd3lLSzBGSEI5anpocTdNQlZKVWNoMHN3YUpZPTU5MEI5ODk2")
                .when().get("search?jql=").jsonPath().getList("issues.fields.summary");
        //get("issues[0].fields.summary");
        System.out.println("response is "+shs);
    }
}
