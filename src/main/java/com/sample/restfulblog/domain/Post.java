package com.sample.restfulblog.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.sample.restfulblog.domain.Author;

@Entity
@Table(name = "post")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id", nullable = false, updatable = true)
    private Author author;

	@Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
	
	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "post_category", joinColumns = { 
			@JoinColumn(name = "post_id", nullable = true, updatable = true) }, 
			inverseJoinColumns = { @JoinColumn(name = "category_id", nullable = false, updatable = false) })
    private Set<Category> categories;

	@Column(name = "content", columnDefinition="TEXT")
    private String content;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the author
	 */
	@JsonIgnore
	public Author getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	@JsonProperty("author")
	public void setAuthor(Author author) {
		this.author = author;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the categories
	 */
	@JsonIgnore
	public Set<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	@JsonProperty("categories")
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
