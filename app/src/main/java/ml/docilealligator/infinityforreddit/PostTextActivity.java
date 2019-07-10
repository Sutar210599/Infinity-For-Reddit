package ml.docilealligator.infinityforreddit;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import pl.droidsonroids.gif.GifImageView;

public class PostTextActivity extends AppCompatActivity {

    static final String EXTRA_SUBREDDIT_NAME = "ESN";

    @BindView(R.id.subreddit_icon_gif_image_view_post_text_activity) GifImageView iconGifImageView;
    @BindView(R.id.subreddit_name_text_view_post_text_activity) TextView subreditNameTextView;
    @BindView(R.id.rules_button_post_text_activity) Button rulesButton;
    @BindView(R.id.post_title_edit_text_post_text_activity) EditText titleEditText;
    @BindView(R.id.post_text_content_edit_text_post_text_activity) EditText contentEditText;

    private RequestManager mGlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_text);

        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp);
        actionBar.setHomeAsUpIndicator(upArrow);

        mGlide = Glide.with(this);

        if(getIntent().hasExtra(EXTRA_SUBREDDIT_NAME)) {
            subreditNameTextView.setText(getIntent().getExtras().getString(EXTRA_SUBREDDIT_NAME));
        } else {
            mGlide.load(R.drawable.subreddit_default_icon)
                    .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(72, 0)))
                    .into(iconGifImageView);
        }

        subreditNameTextView.setOnClickListener(view -> {
            Intent intent = new Intent(this, SubredditSelectionActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return false;
    }
}