package traderV0.trader.user;

import lombok.Getter;

@Getter
public class UserCompanyForm {
    private String userSession;
    private Long companyId;

    public UserCompanyForm(String userSession, Long companyId) {
        this.userSession = userSession;
        this.companyId = companyId;
    }
}
