package com.example.myracipy.Modals;

public interface RecipyDetailsListner {
 void didFetch(RandomRecipyapiresponse responce, String message );
 void didError(String message);

}
