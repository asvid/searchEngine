package pojo;

/**
 * Created by Adam on 2015-11-30.
 */
public class WordData {

    private int senseId;
    private String sense;
    private String example;
    private String wikipediaLink;
    private String imageURL;
    private boolean visibilitry;
    private Object overwrittenSenseId;
    private int partOfSpeechId;

    /**
     * @return The senseId
     */
    public int getSenseId() {
        return senseId;
    }

    /**
     * @param senseId The senseId
     */
    public void setSenseId(int senseId) {
        this.senseId = senseId;
    }

    /**
     * @return The sense
     */
    public String getSense() {
        return sense;
    }

    /**
     * @param sense The sense
     */
    public void setSense(String sense) {
        this.sense = sense;
    }

    /**
     * @return The example
     */
    public String getExample() {
        return example;
    }

    /**
     * @param example The example
     */
    public void setExample(String example) {
        this.example = example;
    }

    /**
     * @return The wikipediaLink
     */
    public String getWikipediaLink() {
        return wikipediaLink;
    }

    /**
     * @param wikipediaLink The wikipediaLink
     */
    public void setWikipediaLink(String wikipediaLink) {
        this.wikipediaLink = wikipediaLink;
    }

    /**
     * @return The imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * @param imageURL The imageURL
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * @return The visibilitry
     */
    public boolean isVisibilitry() {
        return visibilitry;
    }

    /**
     * @param visibilitry The visibilitry
     */
    public void setVisibilitry(boolean visibilitry) {
        this.visibilitry = visibilitry;
    }

    /**
     * @return The overwrittenSenseId
     */
    public Object getOverwrittenSenseId() {
        return overwrittenSenseId;
    }

    /**
     * @param overwrittenSenseId The overwrittenSenseId
     */
    public void setOverwrittenSenseId(Object overwrittenSenseId) {
        this.overwrittenSenseId = overwrittenSenseId;
    }

    /**
     * @return The partOfSpeechId
     */
    public int getPartOfSpeechId() {
        return partOfSpeechId;
    }

    /**
     * @param partOfSpeechId The partOfSpeechId
     */
    public void setPartOfSpeechId(int partOfSpeechId) {
        this.partOfSpeechId = partOfSpeechId;
    }

    @Override
    public String toString() {
        return sense;
    }
}

