package com.ssistudios.stormy.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import com.ssistudios.stormy.R;

/**
 * Created by fc on 7/25/2015.
 */
public class AlertDialogueFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.data_retrieved_alertdialogue_title))
                .setMessage(context.getString(R.string.data_retrieved_alertdialogue_body))
                .setPositiveButton(context.getString(R.string.data_retrieved_alertdialogue_positiveButtonName), null);

        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }
}
