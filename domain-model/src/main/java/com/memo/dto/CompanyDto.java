package com.memo.dto;


import com.memo.enums.CompanyStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class CompanyDto {

    private Long id;

    @Size(max = 100, min = 2, message = "Title should be 2-100 characters long.")
    private String title;

    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" // +111 (202) 555-0125  +1 (202) 555-0125
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"                                  // +111 123 456 789
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$", message = "Phone number is required field and may be in any valid phone number format.")
    // +111 123 45 67 89
    private String phone;

    @Pattern(regexp = "^http(s{0,1})://[a-zA-Z0-9/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9/\\&\\?\\=\\-\\.\\~\\%]*", message = "Website should have a valid format.")
    private String website;

    @NotNull
    @Valid
    private AddressDto address;
    private CompanyStatus companyStatus;


//    String urlPattern = new RegExp('^(https?:\/\/)?'+ // validate protocol
//            '((([a-z\d]([a-z\d-][a-z\d]))\.)+[a-z]{2,}|'+ // validate domain name
//            '((\d{1,3}\.){3}\d{1,3}))'+ // validate OR ip (v4) address
//            '(\:\d+)?(\/[-a-z\d%.~+])'+ // validate port and path
//            '(\?[;&a-z\d%.~+=-])?'+ // validate query string
//            '(\#[-a-z\d_])?$','i'); // validate fragment locator


}
