package cn.net.sexygirls.utils.security;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * @Description:
 * @Author: zule
 * @Date: 2019/5/9
 */
@Component
public class AntUrlPathMatcher implements UrlMatcher {
    private boolean requiresLowerCaseUrl;
    private PathMatcher pathMatcher;

    public AntUrlPathMatcher() {
        this(true);

    }

    public AntUrlPathMatcher(boolean requiresLowerCaseUrl) {
        this.requiresLowerCaseUrl = true;
        this.pathMatcher = new AntPathMatcher();
        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
    }

    @Override
    public Object compile(String path) {
        if (this.requiresLowerCaseUrl) {
            return path.toLowerCase();
        }
        return path;
    }

    public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl) {

        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
    }

    @Override
    public boolean pathMatchesUrl(Object path, String url) {
        if (("/**".equals(path)) || ("**".equals(path))) {
            return true;
        }

        return this.pathMatcher.match((String) path, url);
    }

    @Override
    public String getUniversalMatchPattern() {
        return "/**";
    }

    @Override
    public boolean requiresLowerCaseUrl() {
        return this.requiresLowerCaseUrl;
    }

    @Override
    public String toString() {
        return super.getClass().getName() + "[requiresLowerCase='"
                + this.requiresLowerCaseUrl + "']";
    }
}