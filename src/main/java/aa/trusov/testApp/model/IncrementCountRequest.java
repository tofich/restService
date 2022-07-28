package aa.trusov.testApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IncrementCountRequest implements Serializable {

    @Size(max = 10, message = "id size must be between 0 and 10")
    @NotEmpty
    private String counterId;

    @Min(value= 0L, message = "The value must be positive")
    @NotNull
    private Long incrementCount;

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public Long getIncrementCount() {
        return incrementCount;
    }

    public void setIncrementCount(Long incrementCount) {
        this.incrementCount = incrementCount;
    }
}
