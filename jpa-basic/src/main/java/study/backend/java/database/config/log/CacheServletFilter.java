package study.backend.java.database.config.log;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import study.backend.java.database.config.log.model.BufferedRequestWrapper;
import study.backend.java.database.config.log.model.BufferedResponseWrapper;

@RequiredArgsConstructor
public class CacheServletFilter extends OncePerRequestFilter {

  private final String[] excludeMethods;
  private final String[] excludeUrlPatterns;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    BufferedRequestWrapper requestWrapper = new BufferedRequestWrapper(request);
    BufferedResponseWrapper responseWrapper = new BufferedResponseWrapper(response);

    filterChain.doFilter(requestWrapper, responseWrapper);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    boolean match = false;
    if (hasExcludeMethods()) {
      String method = request.getMethod();
      match = Arrays.stream(this.excludeMethods)
          .anyMatch(m -> StringUtils.equalsIgnoreCase(m, method));
    }
    if (!match && hasExcludePatterns()) {
      AntPathMatcher antPathMatcher = new AntPathMatcher();
      String path = request.getServletPath();
      match = Arrays.stream(this.excludeUrlPatterns)
          .anyMatch(p -> antPathMatcher.match(p, path));
    }
    return match;
  }

  private boolean hasExcludePatterns() {
    return this.excludeUrlPatterns != null && this.excludeUrlPatterns.length > 0;
  }

  private boolean hasExcludeMethods() {
    return this.excludeMethods != null && this.excludeMethods.length > 0;
  }
}
