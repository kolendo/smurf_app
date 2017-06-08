package android.smurf.repositories.rest;

import android.smurf.models.SkiSlope;

import java.util.List;

import software.rsquared.restapi.GetRequest;

/**
 * @author Wojtek Kolendo
 */

public class GetNearbySlopesRequest extends GetRequest<List<SkiSlope>> {

    @Override
    protected void prepareRequest() {
        setUrlSegments("smurf", "rest", "ski_slopes");
    }
}
