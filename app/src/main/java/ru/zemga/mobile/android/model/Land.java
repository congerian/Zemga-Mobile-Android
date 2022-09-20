package ru.zemga.mobile.android.model;

import java.util.ArrayList;
import java.util.List;

public class Land
{
    /**
     * Location inner helper class
     */
    public static class Location
    {
        private final double lon;
        private final double lat;
        public Location(Double lat, Double lon)
        {
            this.lat = lat;
            this.lon = lon;
        }

        public Location(Location other)
        {
            this.lat = other.getLat();
            this.lon = other.getLon();
        }

        public double getLon() { return lon; }
        public double getLat() { return lat; }
    }

    /**
     * URL field helper class
     */
    public static class URLField
    {
        private final String name;
        private final String description;
        private final String URL;

        public URLField(String name, String description, String URL)
        {
            this.name = name;
            this.description = description;
            this.URL = URL;
        }

        public URLField(URLField other)
        {
            this.name = other.getName();
            this.description = other.getDescription();
            this.URL = other.getURL();
        }

        public String getName() { return name; }
        public String getDescription() { return description; }
        public String getURL() { return URL; }
    }


    //Fields helper class
    public static class LandFields
    {
        private final Long      id;
        private final String    name;
        private final String    description;
        private final URLField  previewURL;
        private final String    reglament;
        private final String    kadastr;
        private final String    usage;
        private final Double    square;
        private final String    type;
        private final Double    price;
        private final String    district;
        private final String    address;

        private final List<URLField> docURLs;
        private final List<URLField> photos;
        private final List<Location> borderLocs;

        private final Location location;

        public LandFields(  Long            id,
                            String          name,
                            String          description,
                            URLField        previewURL,
                            String          reglament,
                            String          kadastr,
                            String          usage,
                            Double          square,
                            String          type,
                            Double          price,
                            String          district,
                            String          address,
                            List<URLField>  docURLs,
                            List<URLField>  photos,
                            List<Location>  borderLocs,
                            Location        location)
        {
            this.id = id;
            this.name = name;
            this.description = description;
            this.previewURL  = previewURL;
            this.reglament   = reglament;
            this.kadastr     = kadastr;
            this.usage       = usage;
            this.square      = square;
            this.type        = type;
            this.price       = price;
            this.district    = district;
            this.address     = address;
            this.docURLs     = new ArrayList<>(docURLs);
            this.photos      = new ArrayList<>(photos);
            this.borderLocs  = new ArrayList<>(borderLocs);
            this.location    = location;
        }

        public LandFields( LandFields other)
        {
            this.id = other.getId();
            this.name = other.getName();
            this.description = other.getDescription();
            this.previewURL  = other.getPreviewURL();
            this.reglament   = other.getReglament();
            this.kadastr     = other.getKadastr();
            this.usage       = other.getUsage();
            this.square      = other.getSquare();
            this.type        = other.getType();
            this.price       = other.getPrice();
            this.district    = other.getDistrict();
            this.address     = other.getAdress();
            this.docURLs     = other.getDocURLs();
            this.photos = other.getPhotos();
            this.borderLocs = other.getBorderLocs();
            this.location = other.getLocation();
        }

        public Long             getId()             { return id; }
        public String           getName()           { return name; }
        public String           getDescription()    { return description; }
        public URLField         getPreviewURL()     { return new URLField(previewURL); }
        public String           getReglament()      { return reglament; }
        public String           getKadastr()        { return kadastr; }
        public String           getUsage()          { return usage; }
        public Double           getSquare()         { return square; }
        public String           getType()           { return type; }
        public Double           getPrice()          { return price; }
        public String           getDistrict()       { return district; }
        public String           getAdress()         { return address; }
        public List<URLField>   getDocURLs()        { return new ArrayList<>(docURLs); }
        public List<URLField>   getPhotos()         { return new ArrayList<>(photos); }
        public List<Location>   getBorderLocs()     { return new ArrayList<>(borderLocs); }
        public Location         getLocation()       { return new Location(location); }

    }

    private final LandFields fields;

    public LandFields getFields() {
        return fields;
    }

    public Land (Land other)
    {
        this.fields = new LandFields(other.fields);
    }
    public Land (LandFields fields) {
        this.fields = fields;
    }

}