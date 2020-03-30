package springbootboilerplate.application.user.result;

import cn.printf.springbootboilerplate.usercontext.domain.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Getter
@NoArgsConstructor
@Data
public class ProjectResult {
    private Long id;

    private String username;

    private String email;

    private String phone;

    private Boolean enabled;

    public static ProjectResult of(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, ProjectResult.class);
    }
}
