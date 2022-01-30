package com.example.myracipy.Listner;

import com.example.myracipy.Modals.RandomRecipyapiresponse;

public interface RandonRecipyResponseLintner {
    void didFetch(RandomRecipyapiresponse response , String message);
    void didErropr (String message);


}
