package pages;

import lombok.Data;
import org.openqa.selenium.By;

@Data
public class FirstPage {
    //By gdpr = By.cssSelector("#CookieAlert button");
    By origin= By.id("OriginInput");
    By FirstListElementForOrg= By.id("react-autowhatever-OriginInput-section-0-item-0");

    By destination= By.id("DestinationInput");
    By FirstListElementForDest= By.id("react-autowhatever-DestinationInput-section-0-item-0");

    By ListElements = By.id("react-autowhatever-DestinationInput");

   // By fromListForFlightSearch=By.xpath("//*/ul[@role='listbox']/li");
}
