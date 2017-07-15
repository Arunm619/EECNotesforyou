package com.arunsudharsan.eecnotesforyou;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Arun on 3/20/2017.
 */

public class firebaseinstantidservice extends FirebaseInstanceIdService{
    private static final String REG_TOKEN="REG_TOKEN";
    @Override
    public void onTokenRefresh() {
        String recent_token= FirebaseInstanceId.getInstance().getToken();
    }
}
