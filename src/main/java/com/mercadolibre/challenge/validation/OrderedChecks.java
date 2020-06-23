package com.mercadolibre.challenge.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence(value = { Default.class, SecondCheck.class })
public interface OrderedChecks {
}
