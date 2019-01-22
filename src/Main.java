import sfdc.profiles.Profile;
import sfdc.profiles.profileNodes.*;

public class Main {
    public static void main(String[] args) {
        String profileDirectory = "C:\\Users\\piotr\\IdeaProjects\\goodenergy\\force-app\\main\\default\\profiles\\";
        String[] profilePaths = new String[]{
                profileDirectory + "Admin.profile-meta.xml",
                profileDirectory + "Business Intelligence User.profile-meta.xml",
                profileDirectory + "Customer Operations.profile-meta.xml",
                profileDirectory + "Customer Operations %28Quoting%29.profile-meta.xml",
                profileDirectory + "Customer Service.profile-meta.xml",
                profileDirectory + "DST Sales.profile-meta.xml",
                profileDirectory + "Energy Solution Sales.profile-meta.xml",
                profileDirectory + "Enterprise Sales.profile-meta.xml",
                profileDirectory + "Integration User.profile-meta.xml",
                profileDirectory + "MarketingProfile.profile-meta.xml",
                profileDirectory + "PolSource Admin.profile-meta.xml",
                profileDirectory + "ReadOnly.profile-meta.xml",
                profileDirectory + "SME Sales.profile-meta.xml"
        };

        for (String profilePath : profilePaths) {
            Profile profile = new Profile(profilePath);
            profile.add(new FieldPermission("Meter_Point__c.Generator_ROC_ID__c", true, false));
            profile.add(new ObjectPermission("Meter_Point__c", true, false, true, true, false, false));

            profile.remove(new FieldPermission("Meter_Point__c.Generator_ROC_ID__c"));
            profile.add(new TabVisibility("Application_Log__c", TabVisibility.DefaultOn));
            profile.saveFile();
        }
    }
}
