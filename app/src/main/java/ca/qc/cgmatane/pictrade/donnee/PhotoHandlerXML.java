package ca.qc.cgmatane.pictrade.donnee;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.pictrade.modele.Commerce;
import ca.qc.cgmatane.pictrade.modele.Photo;

public class PhotoHandlerXML extends DefaultHandler implements Dictionnaire{


    private List<Photo> listePhoto = null;
    private StringBuilder donnee = null;
    private Photo photo = null;
    private int id_commerce;


    private boolean isImage;

    public PhotoHandlerXML() {
        super();
    }

    public List<Photo> getListePhoto() {
        return listePhoto;
    }

    public void setListePhoto(List<Photo> listePhoto) {
        this.listePhoto = listePhoto;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase(CLE_LISTE_PHOTO)) {
            // create a new Employee and put it in Map
            id_commerce = Integer.parseInt(attributes.getValue(CLE_ID_COMMERCE));
            // initialize list
            if (listePhoto == null){
                listePhoto = new ArrayList<>();
            }

        } else if (qName.equalsIgnoreCase(CLE_PHOTO)) {
            // create a new Employee and put it in Map
            String id_photo = attributes.getValue(CLE_ID_PHOTO);
            // initialize Employee object and set id attribute
            photo = new Photo();
            photo.setId_photo(Integer.parseInt(id_photo));
            photo.setId_commerce(id_commerce);
            // initialize list
            if (listePhoto == null)
                listePhoto = new ArrayList<>();
        } else if (qName.equalsIgnoreCase(CLE_IMAGE_PHOTO)) {
            isImage = true;
        }

        donnee = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (isImage) {
            photo.setImage(Photo.StringToBitMap(donnee.toString()));
            isImage = false;
        }
        if (qName.equalsIgnoreCase(CLE_PHOTO)) {
            listePhoto.add(photo);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        donnee.append(new String(ch, start, length));
    }
}
