package ru.zemga.mobile.android.model;

import java.util.List;

public class Land
{

    //Location inner helper class
    public static class Location
    {
        private final double lon;
        private final double lat;
        public Location(Double lat, Double lon)
        {
            this.lat = lat;
            this.lon = lon;
        }

        public double getLon() { return lon; }
        public double getLat() { return lat; }
    }

    //Type helper class
    //public enum LandType {IJS, LPH, SNT} // ИЖС == 0, СНТ == 1, ЛПХ == 2

    //URL field helper class
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
            this.docURLs     = docURLs;
            this.photos = photos;
            this.borderLocs = borderLocs;
            this.location = location;
        }

        public Long             getId()             { return id; }
        public String           getName()           { return name; }
        public String           getDescription()    { return description; }
        public URLField         getPreviewURL()     { return previewURL; }
        public String           getReglament()      { return reglament; }
        public String           getKadastr()        { return kadastr; }
        public String           getUsage()          { return usage; }
        public Double           getSquare()         { return square; }
        public String           getType()           { return type; }
        public Double           getPrice()          { return price; }
        public String           getDistrict()       { return district; }
        public String           getAdress()         { return address; }
        public List<URLField>   getDocURLs()        { return docURLs; }
        public List<URLField>   getPhotos()         { return photos; }
        public List<Location>   getBorderLocs()     { return borderLocs; }
        public Location         getLocation()       { return location; }

    }

    private final LandFields fields;

    public Land(LandFields fields) {
        this.fields = fields;
    }

    public LandFields getFields() {
        return fields;
    }

}