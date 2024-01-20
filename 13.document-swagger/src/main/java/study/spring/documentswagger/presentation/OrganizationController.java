package study.spring.documentswagger.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.spring.documentswagger.presentation.model.OrganizationDto;

@Tag(name = "기관 관리")
@RestController
public class OrganizationController {

  @Operation(summary = "기관 리스트 정보를 조회합니다.")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "기관 리스트 조회 성공",
          content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = OrganizationDto.class)
              )
          }
      )
  })
  @Parameters(value = {
      @Parameter(
          name = "organizationCode",
          description = "기관 코드",
          required = false
      )
  })
  @GetMapping("/api/organizations")
  public List<OrganizationDto> findOrganizations(
      @RequestParam(name = "organizationCode", required = false) String organizationCode
  ) {
    return Arrays.asList(new OrganizationDto("A", "서울시"));
  }

}
