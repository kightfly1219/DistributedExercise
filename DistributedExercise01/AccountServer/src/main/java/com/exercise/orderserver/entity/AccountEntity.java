package com.exercise.orderserver.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "account", schema = "sche", catalog = "sche")
@Getter
@Setter
@ToString
public class AccountEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, insertable = false)
    private Integer id;

    @Column(name = "account_id", length = 64)
    private String accountId;

    @Column(name = "name", length = 32)
    private String name;

    @Column(name = "account_role_type", nullable = false)
    private Short accountRoleType;

    @Column(name = "current_password", length = 64)
    private String currentPassword;

    @Column(name = "email_address", length = 256)
    private String emailAddress;

    @Column(name = "is_next_change_password")
    private Boolean isNextChangePassword;

    @Column(name = "incorrect_login_count")
    private Short incorrectLoginCount;

    @Version
    @Column(name = "is_exclusive")
    private Integer isExclusive;

    @Column(name = "pw_reset_token",length = 512)
    private String pwResetToken;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @CreatedBy
    @Column(name = "create_user_id", nullable = false, updatable = false)
    private Integer createUserId;

    @CreatedDate
    @Column(name = "create_date_time", nullable = false, updatable = false)
    private LocalDateTime createdDateTime;

    @LastModifiedBy
    @Column(name = "update_user_id", nullable = false)
    private Integer updateUserId;

    @LastModifiedDate
    @Column(name = "update_date_time", nullable = false)
    private LocalDateTime updateDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountEntity that = (AccountEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(accountRoleType, that.accountRoleType)
                && Objects.equals(accountId, that.accountId)
                && Objects.equals(name, that.name)
                && Objects.equals(currentPassword, that.currentPassword)
                && Objects.equals(emailAddress, that.emailAddress)
                && Objects.equals(isNextChangePassword, that.isNextChangePassword)
                && Objects.equals(incorrectLoginCount, that.incorrectLoginCount)
                && Objects.equals(isExclusive, that.isExclusive)
                && Objects.equals(pwResetToken, that.pwResetToken)
                && Objects.equals(getIsDeleted(), that.getIsDeleted())
                && Objects.equals(getCreateUserId(), that.getCreateUserId())
                && Objects.equals(getCreatedDateTime(), that.getCreatedDateTime())
                && Objects.equals(getUpdateUserId(), that.getUpdateUserId())
                && Objects.equals(getUpdateDateTime(), that.getUpdateDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, name, accountRoleType, currentPassword
                , emailAddress, isNextChangePassword, incorrectLoginCount, isExclusive, pwResetToken
                , getIsDeleted()
                , getCreateUserId(), getCreatedDateTime()
                , getUpdateUserId(), getUpdateDateTime());
    }
}
