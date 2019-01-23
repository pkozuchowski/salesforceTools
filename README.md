# Salesforce Tools
Tools for programmatically managing Salesforce metadata (currently only Profiles).

    String profileDirectory = "...\\force-app\\main\\default\\profiles\\";
    String[] profilePaths = new String[]{
            profileDirectory + "Admin.profile-meta.xml",
            profileDirectory + "Sales.profile-meta.xml",
            profileDirectory + "Service.profile-meta.xml",
    };
    
    for (String profilePath : profilePaths) {
        Profile profile = new Profile(profilePath);
        
        profile.add(new FieldPermission("Account.Custom_Field_1__c", true, false));
        profile.add(new FieldPermission("Account.Custom_Field_2__c", true, true));
        profile.remove(new FieldPermission("Account.Custom_Field_2__c"));
    
        profile.add(new ObjectPermission("Custom_Object__c", true, false, true, true, false, false));
        profile.add(new TabVisibility("Application_Log__c", TabVisibility.DefaultOn));
        profile.saveFile();
    }
