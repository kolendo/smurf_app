package android.smurf.repositories.rest;

import android.smurf.models.SkiSlope;

import java.util.List;

import software.rsquared.restapi.GetRequest;

/**
 * @author Wojtek Kolendo
 */

public class GetSlopeDetailsRequest extends GetRequest<SkiSlope> {

    long skiSlopeId;

    public GetSlopeDetailsRequest(long skiSlopeId) {
        this.skiSlopeId = skiSlopeId;
    }

    @Override
    protected void prepareRequest() {
        setUrlSegments("smurf", "rest", "ski_slopes", "slope", String.valueOf(skiSlopeId));
    }
}
