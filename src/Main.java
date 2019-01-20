import sfdc.profiles.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] profiles = new String[]{
                "Admin.profile-meta.xml",
                "Business Intelligence User.profile-meta.xml",
                "Customer Operations.profile-meta.xml",
                "Customer Operations %28Quoting%29.profile-meta.xml",
                "Customer Service.profile-meta.xml",
                "DST Sales.profile-meta.xml",
                "Energy Solution Sales.profile-meta.xml",
                "Enterprise Sales.profile-meta.xml",
                "Integration User.profile-meta.xml",
                "MarketingProfile.profile-meta.xml",
                "PolSource Admin.profile-meta.xml",
                "ReadOnly.profile-meta.xml",
                "SME Sales.profile-meta.xml"
        };

        for (String profileName : profiles) {
            Profile profile = new Profile("C:\\Users\\piotr\\IdeaProjects\\goodenergy\\force-app\\main\\default\\profiles\\" + profileName);
//            profile.add(new FieldPermission("Meter_Point__c.Generator_ROC_ID__c", false, false));
            profile.saveFile();
        }
    }
}
