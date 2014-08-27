package cleverpumpkintesttask.programmerr47.github.com.flightsviewer.clever_api.responseObjects.summary;

import org.xmlpull.v1.XmlPullParser;

import cleverpumpkintesttask.programmerr47.github.com.flightsviewer.clever_api.util.XmlUtils;

/**
 * @author Michael Spitsin
 * @since 2014-08-27
 */
@SuppressWarnings("unused")
public class Description {
    public static final String TAG = "description";

    private String value;

    private Description(Builder builder) {
        this.value = builder.value;
    }

    public String getValue() {
        return value;
    }

    public static class Builder {
        private String value;

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Description build() {
            return new Description(this);
        }
    }

    /**
     * Creates {@link Description} object from its JSON Counterpart.
     *
     * @param parser given XML parser with stream inside and placed on this object
     * @return new instance of Description or null, if json is null
     */
    public static Description getFromXml(XmlPullParser parser) {
        if (parser == null) {
            return null;
        }

        if (XmlUtils.isCorrect(parser, XmlPullParser.START_TAG, null, TAG)) {
            Builder builder = new Builder();

            String value = XmlUtils.getText(parser);

            if (XmlUtils.isCorrect(parser, XmlPullParser.END_TAG, null, TAG)) {
                builder.setValue(value);
            }

            return builder.build();
        } else {
            return null;
        }
    }
}