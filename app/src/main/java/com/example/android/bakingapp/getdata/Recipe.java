package com.example.android.bakingapp.getdata;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder( {
        "id",
        "name",
        "ingredients",
        "steps",
        "servings",
        "image"
})
public class Recipe  implements Parcelable{

    @JsonProperty("id")
    private Double id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("ingredients")
    private ArrayList<Ingredient> ingredients = null;
    @JsonProperty("steps")
    private ArrayList<Step> steps = null;
    @JsonProperty("servings")
    private Double servings;
    @JsonProperty("image")
    private String image;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Double getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Double id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("ingredients")
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    @JsonProperty("ingredients")
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @JsonProperty("steps")
    public ArrayList<Step> getSteps() {
        return steps;
    }

    @JsonProperty("steps")
    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    @JsonProperty("servings")
    public Double getServings() {
        return servings;
    }

    @JsonProperty("servings")
    public void setServings(Double servings) {
        this.servings = servings;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "quantity",
            "measure",
            "ingredient"
    })
    public class Ingredient implements java.io.Serializable {

        @JsonProperty("quantity")
        private Double quantity;
        @JsonProperty("measure")
        private String measure;
        @JsonProperty("ingredient")
        private String ingredient;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("quantity")
        public Double getQuantity() {
            return quantity;
        }

        @JsonProperty("quantity")
        public void setQuantity(Double quantity) {
            this.quantity = quantity;
        }

        @JsonProperty("measure")
        public String getMeasure() {
            return measure;
        }

        @JsonProperty("measure")
        public void setMeasure(String measure) {
            this.measure = measure;
        }

        @JsonProperty("ingredient")
        public String getIngredient() {
            return ingredient;
        }

        @JsonProperty("ingredient")
        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "shortDescription",
            "description",
            "videoURL",
            "thumbnailURL"
    })
    public class Step implements java.io.Serializable{

        @JsonProperty("id")
        private Double id;
        @JsonProperty("shortDescription")
        private String shortDescription;
        @JsonProperty("description")
        private String description;
        @JsonProperty("videoURL")
        private String videoURL;
        @JsonProperty("thumbnailURL")
        private String thumbnailURL;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("id")
        public Double getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Double id) {
            this.id = id;
        }

        @JsonProperty("shortDescription")
        public String getShortDescription() {
            return shortDescription;
        }

        @JsonProperty("shortDescription")
        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        @JsonProperty("description")
        public String getDescription() {
            return description;
        }

        @JsonProperty("description")
        public void setDescription(String description) {
            this.description = description;
        }

        @JsonProperty("videoURL")
        public String getVideoURL() {
            return videoURL;
        }

        @JsonProperty("videoURL")
        public void setVideoURL(String videoURL) {
            this.videoURL = videoURL;
        }

        @JsonProperty("thumbnailURL")
        public String getThumbnailURL() {
            return thumbnailURL;
        }

        @JsonProperty("thumbnailURL")
        public void setThumbnailURL(String thumbnailURL) {
            this.thumbnailURL = thumbnailURL;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    protected Recipe(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readDouble();
        name = in.readString();
        if (in.readByte() == 0x01) {
            ingredients = new ArrayList<Ingredient>();
            in.readList(ingredients, Ingredient.class.getClassLoader());
        } else {
            ingredients = null;
        }
        if (in.readByte() == 0x01) {
            steps = new ArrayList<Step>();
            in.readList(steps, Step.class.getClassLoader());
        } else {
            steps = null;
        }
        servings = in.readByte() == 0x00 ? null : in.readDouble();
        image = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(id);
        }
        dest.writeString(name);
        if (ingredients == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(ingredients);
        }
        if (steps == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(steps);
        }
        if (servings == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(servings);
        }
        dest.writeString(image);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}