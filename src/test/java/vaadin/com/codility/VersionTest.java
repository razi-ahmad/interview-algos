package vaadin.com.codility;

import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;


public class VersionTest {
    @Test(expected = IllegalArgumentException.class)
    public void test_WhenArgumentIsNull() {
        new Version(null);
    }

    @Test
    public void test_MessageWhenArgumentIsNull() {
        try {
            new Version(null);
        } catch (IllegalArgumentException re) {
            Assert.assertEquals(errorVersionMustNotBeNull, re.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_WhenArgumentIsNotMatched() {
        new Version("1.0.0.0-SNAPSHOT");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_WhenArgumentIsWrongSuffix() {
        try {
            new Version("1.0.0-WRONGSUFFIX");
        }catch (IllegalArgumentException exception){
            Assert.assertEquals(errorVersionMustMatchPattern,exception.getMessage());
            throw  exception;
        }
    }

    @Test
    public void test_WhenArgumentIsValid() {
        Version version = new Version("1.0.0-SNAPSHOT");
        Assert.assertNotNull(version);
    }

    @Test
    public void test_WhenArgumentIsValidWithXSNAPSHOT() {
        Version version = new Version("1-SNAPSHOT");
        Assert.assertNotNull(version);
    }

    @Test
    public void test_WhenArgumentIsValidWithX() {
        Version version = new Version("1");
        Assert.assertNotNull(version);
    }

    @Test
    public void test_WhenArgumentIsValidWithXYSNAPSHOT() {
        Version version = new Version("1.1-SNAPSHOT");
        Assert.assertNotNull(version);
    }

    @Test
    public void test_WhenArgumentIsValidWithXY() {
        Version version = new Version("3.8.0");
        Assert.assertNotNull(version);
    }

    @Test
    public void test_WhenArgumentIsValidWithXYZSNAPSHOT() {
        Version version = new Version("3.8.0-SNAPSHOT");
        Assert.assertNotNull(version);
    }

    @Test
    public void test_WhenArgumentIsValidWithXYZ() {
        Version version = new Version("1.1.1");
        Assert.assertNotNull(version);
    }

    @Test
    public void test_WhenArgumentIsValidWithoutSnapshot() {
        Version version = new Version("1.0.0");
        Assert.assertNotNull(version);
    }

    @Test
    public void test_WhenSnapshotExist() {
        Version version = new Version("1.0.0-SNAPSHOT");
        Assert.assertTrue(version.isSnapshot());
    }

    @Test
    public void test_WhenSnapshotNotExist() {
        Version version = new Version("1.0.0");
        Assert.assertFalse(version.isSnapshot());
    }

    @Test
    public void test_WhenVersionIsEqualToOther() {
        Version v1 = new Version("1.0.0");
        Version v2 = new Version("1.0.0");
        Assert.assertEquals(0, v1.compareTo(v2));
    }

    @Test
    public void test_WhenVersionIsGreaterThanOther() {
        Version v1 = new Version("1.1.0");
        Version v2 = new Version("1.0.0");
        Assert.assertTrue(v1.compareTo(v2) > 0);
    }

    @Test
    public void test_WhenVersionIsLessThanOther() {
        Version v1 = new Version("1.0.0");
        Version v2 = new Version("1.1.0");
        Assert.assertTrue(v1.compareTo(v2) < 0);
    }

    @Test
    public void test_WhenVersionWithSNAPSHOTIsLessThanOther() {
        Version v1 = new Version("1.0.0-SNAPSHOT");
        Version v2 = new Version("1.1.0");
        Assert.assertTrue(v1.compareTo(v2) < 0);
    }

    @Test
    public void test_WhenOtherVersionIsNull() {
        try {
            Version v1 = new Version("1.0.0-SNAPSHOT");
            v1.compareTo(null);
        } catch (IllegalArgumentException re) {
            Assert.assertEquals(errorOtherMustNotBeNull, re.getMessage());
        }
    }

    static final String errorVersionMustNotBeNull = "'version' must not be null!";
    static final String errorOtherMustNotBeNull = "'other' must not be null!";
    static final String errorVersionMustMatchPattern = "'version' must match: 'major.minor.patch(-SNAPSHOT)'!";
}