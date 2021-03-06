/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package src.main.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class depart extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"depart\",\"namespace\":\"src.main.avro\",\"fields\":[{\"name\":\"DepartName\",\"type\":\"string\"},{\"name\":\"contact\",\"type\":\"string\"},{\"name\":\"programs\",\"type\":{\"type\":\"enum\",\"name\":\"Programs\",\"symbols\":[\"HighQuality\",\"Honors\"]}},{\"name\":\"numberOfStudents\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence DepartName;
  @Deprecated public java.lang.CharSequence contact;
  @Deprecated public src.main.avro.Programs programs;
  @Deprecated public int numberOfStudents;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public depart() {}

  /**
   * All-args constructor.
   */
  public depart(java.lang.CharSequence DepartName, java.lang.CharSequence contact, src.main.avro.Programs programs, java.lang.Integer numberOfStudents) {
    this.DepartName = DepartName;
    this.contact = contact;
    this.programs = programs;
    this.numberOfStudents = numberOfStudents;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return DepartName;
    case 1: return contact;
    case 2: return programs;
    case 3: return numberOfStudents;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: DepartName = (java.lang.CharSequence)value$; break;
    case 1: contact = (java.lang.CharSequence)value$; break;
    case 2: programs = (src.main.avro.Programs)value$; break;
    case 3: numberOfStudents = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'DepartName' field.
   */
  public java.lang.CharSequence getDepartName() {
    return DepartName;
  }

  /**
   * Sets the value of the 'DepartName' field.
   * @param value the value to set.
   */
  public void setDepartName(java.lang.CharSequence value) {
    this.DepartName = value;
  }

  /**
   * Gets the value of the 'contact' field.
   */
  public java.lang.CharSequence getContact() {
    return contact;
  }

  /**
   * Sets the value of the 'contact' field.
   * @param value the value to set.
   */
  public void setContact(java.lang.CharSequence value) {
    this.contact = value;
  }

  /**
   * Gets the value of the 'programs' field.
   */
  public src.main.avro.Programs getPrograms() {
    return programs;
  }

  /**
   * Sets the value of the 'programs' field.
   * @param value the value to set.
   */
  public void setPrograms(src.main.avro.Programs value) {
    this.programs = value;
  }

  /**
   * Gets the value of the 'numberOfStudents' field.
   */
  public java.lang.Integer getNumberOfStudents() {
    return numberOfStudents;
  }

  /**
   * Sets the value of the 'numberOfStudents' field.
   * @param value the value to set.
   */
  public void setNumberOfStudents(java.lang.Integer value) {
    this.numberOfStudents = value;
  }

  /** Creates a new depart RecordBuilder */
  public static src.main.avro.depart.Builder newBuilder() {
    return new src.main.avro.depart.Builder();
  }
  
  /** Creates a new depart RecordBuilder by copying an existing Builder */
  public static src.main.avro.depart.Builder newBuilder(src.main.avro.depart.Builder other) {
    return new src.main.avro.depart.Builder(other);
  }
  
  /** Creates a new depart RecordBuilder by copying an existing depart instance */
  public static src.main.avro.depart.Builder newBuilder(src.main.avro.depart other) {
    return new src.main.avro.depart.Builder(other);
  }
  
  /**
   * RecordBuilder for depart instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<depart>
    implements org.apache.avro.data.RecordBuilder<depart> {

    private java.lang.CharSequence DepartName;
    private java.lang.CharSequence contact;
    private src.main.avro.Programs programs;
    private int numberOfStudents;

    /** Creates a new Builder */
    private Builder() {
      super(src.main.avro.depart.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(src.main.avro.depart.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.DepartName)) {
        this.DepartName = data().deepCopy(fields()[0].schema(), other.DepartName);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.contact)) {
        this.contact = data().deepCopy(fields()[1].schema(), other.contact);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.programs)) {
        this.programs = data().deepCopy(fields()[2].schema(), other.programs);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.numberOfStudents)) {
        this.numberOfStudents = data().deepCopy(fields()[3].schema(), other.numberOfStudents);
        fieldSetFlags()[3] = true;
      }
    }
    
    /** Creates a Builder by copying an existing depart instance */
    private Builder(src.main.avro.depart other) {
            super(src.main.avro.depart.SCHEMA$);
      if (isValidValue(fields()[0], other.DepartName)) {
        this.DepartName = data().deepCopy(fields()[0].schema(), other.DepartName);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.contact)) {
        this.contact = data().deepCopy(fields()[1].schema(), other.contact);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.programs)) {
        this.programs = data().deepCopy(fields()[2].schema(), other.programs);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.numberOfStudents)) {
        this.numberOfStudents = data().deepCopy(fields()[3].schema(), other.numberOfStudents);
        fieldSetFlags()[3] = true;
      }
    }

    /** Gets the value of the 'DepartName' field */
    public java.lang.CharSequence getDepartName() {
      return DepartName;
    }
    
    /** Sets the value of the 'DepartName' field */
    public src.main.avro.depart.Builder setDepartName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.DepartName = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'DepartName' field has been set */
    public boolean hasDepartName() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'DepartName' field */
    public src.main.avro.depart.Builder clearDepartName() {
      DepartName = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'contact' field */
    public java.lang.CharSequence getContact() {
      return contact;
    }
    
    /** Sets the value of the 'contact' field */
    public src.main.avro.depart.Builder setContact(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.contact = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'contact' field has been set */
    public boolean hasContact() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'contact' field */
    public src.main.avro.depart.Builder clearContact() {
      contact = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'programs' field */
    public src.main.avro.Programs getPrograms() {
      return programs;
    }
    
    /** Sets the value of the 'programs' field */
    public src.main.avro.depart.Builder setPrograms(src.main.avro.Programs value) {
      validate(fields()[2], value);
      this.programs = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'programs' field has been set */
    public boolean hasPrograms() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'programs' field */
    public src.main.avro.depart.Builder clearPrograms() {
      programs = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'numberOfStudents' field */
    public java.lang.Integer getNumberOfStudents() {
      return numberOfStudents;
    }
    
    /** Sets the value of the 'numberOfStudents' field */
    public src.main.avro.depart.Builder setNumberOfStudents(int value) {
      validate(fields()[3], value);
      this.numberOfStudents = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'numberOfStudents' field has been set */
    public boolean hasNumberOfStudents() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'numberOfStudents' field */
    public src.main.avro.depart.Builder clearNumberOfStudents() {
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    public depart build() {
      try {
        depart record = new depart();
        record.DepartName = fieldSetFlags()[0] ? this.DepartName : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.contact = fieldSetFlags()[1] ? this.contact : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.programs = fieldSetFlags()[2] ? this.programs : (src.main.avro.Programs) defaultValue(fields()[2]);
        record.numberOfStudents = fieldSetFlags()[3] ? this.numberOfStudents : (java.lang.Integer) defaultValue(fields()[3]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
