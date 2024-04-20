package IssueCreate;

import io.restassured.RestAssured;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class CreateIssue {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://stock-market-123.atlassian.net/rest/api/2";

        try (FileInputStream file = new FileInputStream("C:\\Users\\Welcome\\Desktop\\JiraData.xlsx")) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet("Jira");

            int rowCount = sheet.getLastRowNum() + 1;
            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);

                Cell descriptionCell = row.getCell(0);
                Cell summaryCell = row.getCell(1);

                String description = descriptionCell.getStringCellValue();
                String summary = summaryCell.getStringCellValue();

                // Check if the issue already exists
                if (!issueExists(summary)) {
                    RestAssured.given()
                            .header("Authorization", "Basic bXV0dHVyYWphMTk5NkBnbWFpbC5jb206QVRBVFQzeEZmR0YwZE5FZU0yNEhpQ1VhU1RScHZRaEVuN0JxZzRsZ3JZX3VxRHN3d0ItRzdKaTR5VlRyeDVzcjZMVHBreWlISHJldTRGTUEwb1c1MHNLYkctZU1hMXRrb1NqQm0tYjdrUndBRlF1Z00tNHY3OVQ1emNpQTZyV2JZQ3B1OWdweHF6OFpzZWRsckxoVHlIcTFCZG1Fd3lLSzBGSEI5anpocTdNQlZKVWNoMHN3YUpZPTU5MEI5ODk2")
                            .header("Content-Type", "application/json")
                            .body("{\n" +
                                    "    \"fields\": {\n" +
                                    "      \"project\": {\n" +
                                    "        \"key\": \"STOC\"\n" +
                                    "      },\n" +
                                    "      \"summary\": \"" + summary + "\",\n" +
                                    "      \"description\": \"" + description + "\",\n" +
                                    "      \"issuetype\": {\n" +
                                    "        \"name\": \"Task\"\n" +
                                    "      }\n" +
                                    "    }\n" +
                                    "  }")
                            .when().post("issue").then().assertThat().statusCode(201).log().all();
                } else {
                    System.out.println("Issue already exists with summary: " + summary);
                }
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean issueExists(String summary) {
        RestAssured.baseURI = "https://stock-market-123.atlassian.net/rest/api/2";

        List<String> shs = RestAssured.given()
                .header("Authorization", "Basic bXV0dHVyYWphMTk5NkBnbWFpbC5jb206QVRBVFQzeEZmR0YwZE5FZU0yNEhpQ1VhU1RScHZRaEVuN0JxZzRsZ3JZX3VxRHN3d0ItRzdKaTR5VlRyeDVzcjZMVHBreWlISHJldTRGTUEwb1c1MHNLYkctZU1hMXRrb1NqQm0tYjdrUndBRlF1Z00tNHY3OVQ1emNpQTZyV2JZQ3B1OWdweHF6OFpzZWRsckxoVHlIcTFCZG1Fd3lLSzBGSEI5anpocTdNQlZKVWNoMHN3YUpZPTU5MEI5ODk2")
                .when().get("search?jql=").jsonPath().get("issues.fields.summary");
        //get("issues[0].fields.summary");
        System.out.println("response is " + shs);
        return shs.contains(summary);
    }

}
