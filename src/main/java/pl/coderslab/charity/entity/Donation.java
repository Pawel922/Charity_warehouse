package pl.coderslab.charity.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int quantity;

    @ManyToMany
    @JoinTable(name = "donation_categories",
            joinColumns = @JoinColumn(name = "donation_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private String street;

    private String city;

    private String zipCode;

    private String phoneNumber;

    private LocalDate pickUpDate;

    private LocalTime pickUpTime;

    private String pickUpComment;
    
    private LocalDate receiveDate;
    
    //status 0 means that donation was not received
    //status 1 means that donation was received
    private long status = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getPickUpComment() {
        return pickUpComment;
    }

    public void setPickUpComment(String pickUpComment) {
        this.pickUpComment = pickUpComment;
    }

    public long getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDate getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(LocalDate receiveDate) {
		this.receiveDate = receiveDate;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donation donation = (Donation) o;
        return id == donation.id &&
                quantity == donation.quantity &&
                Objects.equals(categories, donation.categories) &&
                Objects.equals(institution, donation.institution) &&
                Objects.equals(street, donation.street) &&
                Objects.equals(city, donation.city) &&
                Objects.equals(zipCode, donation.zipCode) &&
                Objects.equals(phoneNumber, donation.phoneNumber) &&
                Objects.equals(pickUpDate, donation.pickUpDate) &&
                Objects.equals(pickUpTime, donation.pickUpTime) &&
                Objects.equals(pickUpComment, donation.pickUpComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, categories, institution, street, city, zipCode, phoneNumber, pickUpDate, pickUpTime, pickUpComment);
    }
}
