package preonboarding.backend.utils;

import static lombok.AccessLevel.PRIVATE;

import java.net.URI;
import lombok.NoArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

@NoArgsConstructor(access = PRIVATE)
public class UriBuilder {


    public static URI createUri(String defaultUrl) {
        return UriComponentsBuilder
                .newInstance()
                .path(defaultUrl)
                .build()
                .toUri();
    }

    public static URI createUri(String defaultUrl, long resourceId) {
        return UriComponentsBuilder
                .newInstance()
                .path(defaultUrl + "/{resource-id}")
                .buildAndExpand(resourceId)
                .toUri();
    }
}
