package com.handsdown.account.model;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "accounts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

  @Id private String name;

  private Date lastSeen;

  @Valid private List<Item> sales;

  @Length(min = 0, max = 20_000)
  private String note;
}
