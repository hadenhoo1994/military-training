package cn.iutils.mt.entity;

import org.springframework.format.annotation.DateTimeFormat;
import cn.iutils.sys.entity.DataEntity;
import java.util.Date;

/**
* 日记表
* @author iutils.cn
* @version 1.0
*/
public class Diary extends DataEntity<Diary> {

    private static final long serialVersionUID = 1L;


    public Diary() {
        super();
    }

    public Diary(String id){
        super(id);
    }

    private Diary(Builder builder) {
        super();
        setId(builder.id);
        setCreateBy(builder.createBy);
        setCreateDate(builder.createDate);
        setUpdateBy(builder.updateBy);
        setUpdateDate(builder.updateDate);
        setRemarks(builder.remarks);
        if (builder.status != null && builder.status.length() != 0) {
            setStatus(builder.status);
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
        private String createBy;
        private Date createDate;
        private String updateBy;
        private Date updateDate;
        private String remarks;
        private String status;

        private Builder() {
            super();
        }

        public Diary build() {
            return new Diary(this);
        }


        public Builder id(String id) {
            this.id = id;
            return this;
        }


        public Builder createBy(String createBy) {
            this.createBy = createBy;
            return this;
        }


        public Builder createDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }


        public Builder updateBy(String updateBy) {
            this.updateBy = updateBy;
            return this;
        }


        public Builder updateDate(Date updateDate) {
            this.updateDate = updateDate;
            return this;
        }


        public Builder remarks(String remarks) {
            this.remarks = remarks;
            return this;
        }


        public Builder status(String status) {
            this.status = status;
            return this;
        }

    }

}
