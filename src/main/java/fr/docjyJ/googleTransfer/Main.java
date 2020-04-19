package fr.docjyJ.googleTransfer;

import fr.docjyJ.googleTransfer.api.GoogleOauth;

import static fr.docjyJ.googleTransfer.Lang.question;
import static fr.docjyJ.googleTransfer.api.ContactsData.transferContacts;
import static fr.docjyJ.googleTransfer.api.GoogleOauth.getService;
import static fr.docjyJ.googleTransfer.api.YouTubeData.*;

public class Main {
    public static void main(String[] args) throws Exception {
        GoogleOauth.Service compteA = getService(Lang.FIRST_STEP);
        GoogleOauth.Service compteB = getService(Lang.SECOND_STEP);
        if(question("like"))
            transferLike(compteA.getYoutubeService(),compteB.getYoutubeService(),"","like");
        if(question("dislike"))
            transferLike(compteA.getYoutubeService(),compteB.getYoutubeService(),"","dislike");
        if(question("subscriptions"))
            transferSubscriptions(compteA.getYoutubeService(),compteB.getYoutubeService(),"");
        if(question("playlist"))
            transferPlaylist(compteA.getYoutubeService(),compteB.getYoutubeService(),"");
        if(question("contacts"))
            transferContacts(compteA.getContactsService(),compteB.getContactsService());
    }
}


