package info.noname.moviehub;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by AKamenov on 10/9/2016.
 */

public class AlertDialogManager {

    public void showAlert(Context ctx, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();

        dialog.setTitle(title);
        //dialog.setIcon();
        dialog.setMessage(message);
        dialog.show();
    }
}
